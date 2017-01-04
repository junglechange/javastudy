package com.jungle.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public class VolatileTest {
    /**
     * ����volatile���Ա�֤��ȡ�����������µģ����Ǻ����ʹ���޸Ĺ��̣��������ܱ������߳��޸�
     * �����������������Ҫͬ��
     * ����ʹ��AtomicInteger
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
                    //�����ӳ�1���룬ʹ�ý������
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
                    //�����ӳ�1���룬ʹ�ý������
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
