package com.jungle.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/1/5 0005.
 */
public class SimpleThreadPool {
}

interface ThreadPool {
    /**
     * simulate {@link java.util.concurrent.ExecutorService shutdown}
     * @param job
     */
    void summit(MyRunnable job);
    void shutdown();
}

/**
 * simulate {@link java.lang.Runnable}
 */
interface MyRunnable{
    void run();
}

class SimpleFixedThreadPool implements ThreadPool {
    private static final int MAX_SIZE = 10;
    private int size = 0;
    private List<Worker> workerList = new LinkedList<Worker>();
    private List<MyRunnable> jobQ = new LinkedList<MyRunnable>();
    private List<Thread> threads = new LinkedList<Thread>();
    private volatile boolean running = true;
    private volatile boolean shutdowning = false;
    private final Object shutdownLock = new Object();

    public SimpleFixedThreadPool(int size){
        if(size>MAX_SIZE){
            size = MAX_SIZE;
        }
        this.size = size;
        for(int i=0;i<size;i++){
            Thread workerT = new Thread(new Worker(),"MyThreadPool-"+i);
            threads.add(workerT);
            //workerList.add(new Worker());
            workerT.start();

        }
    }

    public void summit(MyRunnable job) {
        if (running){
            synchronized (jobQ){
                jobQ.add(job);
                jobQ.notify();
            }
        }
    }

    public void shutdown() {
        running=false;
        synchronized (jobQ){
            if (jobQ.isEmpty()){
                for(Thread thread:threads){
                    thread.interrupt();//maybe occur bug
                    //System.out.println(thread.getState());
                }
            }else {
                shutdowning = true;
            }
        }
    }

    class Worker implements Runnable{

        public void run() {
            MyRunnable job = null;
            while (running){
                synchronized (jobQ){
                    if(jobQ.isEmpty()){
                        try {
                            jobQ.wait();
                        } catch (InterruptedException e) {
                            //e.printStackTrace();
                            String tN = Thread.currentThread().getName();
                            System.out.println(tN+" is interrupted");
                            break;
                        }
                    }
                    job = jobQ.remove(0);
                }
                job.run();
            }
            while (shutdowning){
                synchronized (jobQ){
                    if(jobQ.isEmpty()){
                        break;
                    }
                    job = jobQ.remove(0);
                }
                job.run();
            }
            shutdowning = false;
        }
    }
}

class MyJob implements MyRunnable{
    private static AtomicInteger idAtomic = new AtomicInteger(0);
    private int id = -1;
    public MyJob(){
        id=idAtomic.addAndGet(1);
    }
    public void run(){
        String tN = Thread.currentThread().getName();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tN+":hello! i am job-"+id);
    }
}

class MainClass {
    public static void main(String[] args){
        ThreadPool threadPool = new SimpleFixedThreadPool(3);
        final int jobNum = 40;
        for(int i=0;i<jobNum;i++){
            MyJob job = new MyJob();
            threadPool.summit(job);
        }
//        try {
//            Thread.sleep(5000);//test interrupt wait thread
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        threadPool.shutdown();
    }
}
