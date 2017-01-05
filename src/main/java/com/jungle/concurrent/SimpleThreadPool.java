package com.jungle.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2017/1/5 0005.
 */
public class SimpleThreadPool {
}

interface ThreadPool {
    void summit(Runnable job);
    void shutdown();
}

class SimpleFixedThreadPool implements ThreadPool {
    private int size = 0;
    private List<Worker> workerList = new LinkedList<Worker>();
    private BlockingQueue<Runnable> jobQ = new LinkedBlockingDeque<Runnable>();

    public SimpleFixedThreadPool(int size){
        this.size = size;
        for(int i=0;i<size;i++){
            workerList.add(new Worker());

        }
    }

    public void summit(Runnable job) {

    }

    public void shutdown() {

    }

    class Worker implements Runnable{

        public void run() {

        }
    }
}

class MainClass {
    public static void main(String[] args){
        Thread t =new Thread(new Runnable() {
            public void run() {
                String tN = Thread.currentThread().getName();
                System.out.println(tN+":hello");
            }
        });
        t.run();
        t.start();
    }
}
