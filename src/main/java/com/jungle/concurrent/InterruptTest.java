package com.jungle.concurrent;

/**
 * Created by yunjiang on 2017/1/17.
 */
public class InterruptTest {
    public static void main(String[] args){
        String tn = Thread.currentThread().getName();
        Thread ta = new Thread(new Runnable() {
            @Override
            public void run() {
                String tn = Thread.currentThread().getName();
                System.out.println(tn);
                while (true){
                try{
                    Thread.sleep(10000);
                }catch (InterruptedException e){
                    System.out.println(tn+": interrupt");
                    Thread.currentThread().interrupt();
                }
                }
            }
        });
        ta.start();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println(tn+": interrupt");
            Thread.currentThread().interrupt();
        }
        ta.interrupt();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println(tn+": interrupt");
            Thread.currentThread().interrupt();
        }
        if(ta.isInterrupted()){
            System.out.println(tn+": interrupt ta");
        }
        //ta.interrupt();

    }
}
