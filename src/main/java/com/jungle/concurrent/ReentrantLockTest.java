package com.jungle.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/1/1 0001.
 * 打印1到9这9个数字，由A线程先打印1，2，3，然后由B线程打印4,5,6，然后再由A线程打印7，8，9.
 */
public class ReentrantLockTest {

    public static int printI = 1;

    public static void  main(String[] args) throws InterruptedException {
        Lock reenLock = new ReentrantLock();
        Condition threeCondition = reenLock.newCondition();
        Condition sixCondition = reenLock.newCondition();
        Thread ta = new Thread(new TA(reenLock,threeCondition,sixCondition),"TA");
        Thread tb = new Thread(new TB(reenLock,sixCondition,threeCondition),"TB");
        ta.start();
        tb.start();
    }

    public static class TA implements Runnable {
        private Lock lock = null;
        private Condition sigCondition = null;
        private Condition watCondition = null;
        public TA(Lock lock, Condition sCondition, Condition wCondition){
            this.lock = lock;
            this.sigCondition = sCondition;
            this.watCondition = wCondition;
        }

        public void run() {
            String tN = Thread.currentThread().getName();
            lock.lock();
            try{
                while(printI<4){
                    System.out.println(tN+":"+printI++);
                }
                sigCondition.signal();
                while (printI<7){
                    watCondition.await();
                }
                while (printI<10){
                    System.out.println(tN+":"+printI++);
                }
            }catch (InterruptedException e){
                System.out.println(tN+" interrupt."+e);
            }
            finally {
                lock.unlock();
            }
        }
    }

    public static class TB implements Runnable {
        private Lock lock = null;
        private Condition sigCondition = null;
        private Condition watCondition = null;
        public TB(Lock lock, Condition sCondition, Condition wCondition){
            this.lock = lock;
            this.sigCondition = sCondition;
            this.watCondition = wCondition;
        }

        public void run() {
            String tN = Thread.currentThread().getName();
            lock.lock();
            try{
                while (printI<4){
                    watCondition.await();
                }
                while (printI<7){
                    Thread.sleep(10);
                    System.out.println(tN+":"+printI++);
                }
                sigCondition.signal();
            }catch (InterruptedException e){
                System.out.println(tN+" interrupt."+e);
            }
            finally {
                lock.unlock();
            }
        }
    }
}
