package com.jungle.jvm;

import java.util.Objects;

/**
 * Created by Administrator on 2016/11/7 0007.
 */
public class GCTest {
    public static void main(String[] args){
        testAllocation();
    }

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     */
    public static void testAllocation(){
        final int bytes1MB = 1024*1024;
        byte[] b1 = new byte[2*bytes1MB];
        byte[] b2 = new byte[2*bytes1MB];
        byte[] b3 = new byte[2*bytes1MB];

        byte[] b4 = new byte[2*bytes1MB];

        byte[] b5 = new byte[2*bytes1MB];
        byte[] b6 = new byte[2*bytes1MB];
        byte[] b7 = new byte[2*bytes1MB];

    }
}
