package com.jungle.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/1/6 0006.
 */
public class BlockingQueneTest {
    public static void main(String[] args){
        final BlockingQueue blockingQueue = new ArrayBlockingQueue(2);

        Thread wThread = new Thread(new Runnable() {
            public void run() {
                final int maxNum = 100;
                for(int i=0;i<maxNum;i++){
                    try {
                        blockingQueue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try{
                        Thread.sleep(2000);
                    }catch (Exception e){

                    }
                }

            }
        });

        Thread rThread = new Thread(new Runnable() {
            public void run() {
                int rNum = 0;
                try {
                    rNum = (Integer)blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("read num:"+rNum);
            }
        });

        wThread.start();
        rThread.start();
    }

    enum TestEnum {
        A,B;
    }


}
