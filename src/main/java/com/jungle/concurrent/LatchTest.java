package com.jungle.concurrent;



import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/1/17 0017.
 */
public class LatchTest {
    private static final int TNUM = 4;
    private final CountDownLatch startGate = new CountDownLatch(1);
    private final CountDownLatch endGate = new CountDownLatch(4);
    public static void main(String[] args){

        new LatchTest().test();
    }

    public void test(){
        for(int i=0;i<TNUM;++i){
            new Thread(new WorkerT(startGate,endGate)).start();
        }
        Thread otherWaitEndGateT = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    endGate.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("other thread wait over");
            }
        });
        otherWaitEndGateT.start();
        String tn = Thread.currentThread().getName();
        System.out.println(tn+" open gate");
        startGate.countDown();
        System.out.println(tn+" wait end gate");
        try {
            endGate.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tn+"  end gate open");
    }

    static class WorkerT implements Runnable{
        private final CountDownLatch startGate;
        private final CountDownLatch endGate;

        public WorkerT(CountDownLatch sgate,CountDownLatch egate){
            startGate = sgate;
            endGate = egate;
        }

        @Override
        public void run() {
            try {
                startGate.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String tn = Thread.currentThread().getName();
            System.out.println(tn+" working");
            endGate.countDown();
            System.out.println(tn+" wait end gate");
            try {
                endGate.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tn+" end gate open");
        }
    }


}
