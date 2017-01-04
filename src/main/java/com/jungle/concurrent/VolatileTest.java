package com.jungle.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public class VolatileTest {
    /**
     * 尽管volatile可以保证读取变量总是最新的，但是后面的使用修改过程，变量可能被其它线程修改
     * 所以这个变量还是需要同步
     * 或者使用AtomicInteger
     */
    public static AtomicInteger aNum = new AtomicInteger();
    public static volatile int num=0;
    public static int incNum = 1000;
    public static void main(String[] args){
        String tN = Thread.currentThread().getName();
        Thread ta = new Thread(new Runnable() {
            public void run() {
                String tN = Thread.currentThread().getName();
                for(int i = 0; i<incNum;i++){
                    //这里延迟1毫秒，使得结果明显
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
//                    synchronized (VolatileTest.class){
                        //num++;
                    num=aNum.addAndGet(1);
//                    }

                    System.out.println(tN+":"+num);
                }
            }
        },"TA");
        Thread tb = new Thread(new Runnable() {
            public void run() {
                String tN = Thread.currentThread().getName();
                for(int i = 0; i<incNum;i++){
                    //这里延迟1毫秒，使得结果明显
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
//                    synchronized (VolatileTest.class){
                        //num++;
                    num=aNum.addAndGet(1);
//                    }
                    System.out.println(tN+":"+num);
                }
            }
        },"TB");
        ta.start();
        tb.start();
        //System.out.println(tN+":"+num);
    }

    public String test(String a, String b, String c){
        return a+b+c;
    }
}
