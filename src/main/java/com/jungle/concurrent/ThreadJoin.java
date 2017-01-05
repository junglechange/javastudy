package com.jungle.concurrent;

/**
 * Created by Administrator on 2017/1/1 0001.
 */
public class ThreadJoin {
    public static int i = 0;
    public static final int MAX_NUM = 100;
    public static void main(String[] args){
        System.out.println("main:" + i);
        Thread t = new Thread(new TInc(),"t");




        t.start();
        while (i<MAX_NUM){
            try{
                Thread.sleep(100);
            }catch (Exception e){

            }
            i = i + 2;
            System.out.println("main:"+i);
        }

        System.out.println("t join start");
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t join end");
        while (i<MAX_NUM){
            try{
                Thread.sleep(100);
            }catch (Exception e){

            }
            i = i + 2;
            System.out.println("main:"+i);
        }

    }

    static class TInc implements Runnable{

        public void run() {
            String tName = Thread.currentThread().getName();
            while (i<MAX_NUM){
                try{
                    Thread.sleep(50);
                }catch (Exception e){

                }
                i=i+2;
                System.out.println(tName+":" + i);
            }
        }
    }
}
