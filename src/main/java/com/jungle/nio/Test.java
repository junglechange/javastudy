package com.jungle.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class Test {
    public static final int TEST_CONSTA = 1;
    public static final String TEST_CONSTS = "a";
    public static void main(String[] args) {
        String testConst = "b";
        char s = 257;
        System.out.println(s);
        try {
            InputStream ins = new FileInputStream(new File("s"));
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        Object a = new ITest() {
            @Override
            public void print() {

            }
        };

        class TestInner {
            public static final int a =1;
        }
        class TestInner1 {
            public static final int a =1;
        }


    }

    interface ITest{
        void print();
    }

    class TestInner {
        public static final int a =1;
    }

    public static class TestInnerS{
        public static final int a =1;
    }
}

class Test1 {
    public  static final int a = 1;
}
