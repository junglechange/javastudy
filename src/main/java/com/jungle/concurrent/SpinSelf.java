package com.jungle.concurrent;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public class SpinSelf {
    public static int printI = 1;
    public static  volatile boolean aPrint = true;
    public static int MAX_NUM = 100;
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
            while (printI < MAX_NUM) {

                    try {
//自旋空转
                        while (!aPrint){
                        }
                        incPrint(tN);

                    } catch (Exception e) {

                    }

            }
        }
    }

    public static class TB implements Runnable {
        public void run() {
            Object lock = WaitNotifyTest.class;
            String tN = Thread.currentThread().getName();
            while (printI < MAX_NUM) {

                    try {
                        //自旋空转
                        while(aPrint){

                        }
                        incPrint(tN);

                    } catch (Exception e) {

                    }

            }
        }
    }

    private static void incPrint(String tN) {
        if (printI<MAX_NUM){
            for (int i = printI; i < printI + 3; i++) {
                System.out.println(tN + ":" + i);
            }
            System.out.println("\n");
            printI = printI +3;
            aPrint = !aPrint;
        }
    }
}
