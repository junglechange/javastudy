package com.jungle.concurrent;

/**
 * Created by Administrator on 2017/1/1 0001.
 * ��ӡ1��9��9�����֣���A�߳��ȴ�ӡ1��2��3��Ȼ����B�̴߳�ӡ4,5,6��Ȼ������A�̴߳�ӡ7��8��9.
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
                        //������Ըĳ�������ת�����磬��wait��ͬ���ǣ�������ռ��cpuʱ�䣬
                        // ��wait���ó�cpu���̱߳�Ϊwait״̬��Ҳ��һ���������Զ��Ż���
                        //���Ż������������jvm��13.3
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
                        //������Ըĳ�������ת������/
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
