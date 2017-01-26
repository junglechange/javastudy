package com.jungle.jvm;

/**
 * Created by yunjiang on 2017/1/20.
 */
public class HandlePromotion {
    public static void main(String[] args){
        testAllocation();
    }

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:-HandlePromotionFailure
     * -XX:+PrintTenuringDistribution
     * eden 9M from 1M to 1M tenured 10M
     * -Xloggc:gctest.log
     * -XX:+HeapDumpOnOutOfMemoryError
     */
    public static void testAllocation(){
        final int bytes1MB = 1024*1024;
        byte[] b1 = new byte[2*bytes1MB];
        byte[] b2 = new byte[2*bytes1MB];
        //byte[] b3 = new byte[2*bytes1MB];
        byte[] b3_1 = new byte[1*bytes1MB];
        byte[] b3_2 = new byte[1*bytes1MB];
        //System.out.println(b3.length);
        //WeakReference<Object> wo = new WeakReference<Object>(new Object());//live before next gc
        //System.out.println(wo.get().toString());
        byte[] b4 = new byte[1*bytes1MB];//ygc
        //System.out.println(wo.get().toString());//wo ref obj is collected by gc,Exception in thread "main" java.lang.NullPointerException

        //byte[] b5 = new byte[2*bytes1MB];
        //byte[] b6 = new byte[2*bytes1MB];
        //byte[] b7 = new byte[2*bytes1MB];
        //byte[] b8 = new byte[1*bytes1MB];
        //byte[] b9 = new byte[1*bytes1MB];
        //byte[] b10 = new byte[1*bytes1MB];
        //byte[] b9 = new byte[2*bytes1MB];
        //byte[] b10 = new byte[1*bytes1MB];

    }
}
