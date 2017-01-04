package com.jungle.concurrent;

/**
 * Created by Administrator on 2017/1/1 0001.
 * 打印1到9这9个数字，由A线程先打印1，2，3，然后由B线程打印4,5,6，然后再由A线程打印7，8，9.
 */
public class WaitNotifyTest {
    public static int printI = 1;
    public static  boolean aPrint = true;
    public static void main(String[] args){
        Thread ta = new Thread(new Thread(new TA()),"TA");
        Thread tb = new Thread(new Thread(new TB()),"TB");
        ta.start();
        tb.start();

    }

    public static class TA implements Runnable {
        public void run() {
            Object lock = WaitNotifyTest.class;
            String tN = Thread.currentThread().getName();
            while (printI < 10) {
                synchronized (lock) {
                    try {
//                        while (!aPrint) {
//                            lock.wait();
//                        }
//                        incPrint(tN);
                        //这里可以改成自旋空转，例如，与wait不同的是，自旋会占用cpu时间，
                        // 而wait会让出cpu，线程变为wait状态，也不一定？锁会自动优化？
                        //锁优化见《深入理解jvm》13.3
                        if(!aPrint){

                        }else {
                            incPrint(tN);
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    public static class TB implements Runnable {
        public void run() {
            Object lock = WaitNotifyTest.class;
            String tN = Thread.currentThread().getName();
            while (printI < 10) {
                synchronized (lock) {
                    try {
//                        while (aPrint) {
//                            lock.wait();
//                        }
//                        incPrint(tN);
                        //这里可以改成自旋空转，例如/
                        if(aPrint){

                        }else {
                            incPrint(tN);
                        }

                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    private static void incPrint(String tN) {
        if (printI<10){
            for (int i = printI; i < printI + 3; i++) {
                System.out.println(tN + ":" + i);
            }
            printI = printI +3;
            aPrint = !aPrint;
            WaitNotifyTest.class.notify();
        }
    }
}
