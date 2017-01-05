package com.jungle.jvm;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class ClassLoad {
    public static void main(String[] args){
        //System.out.println(B.ID);
    }

    public void test(){
    }

    private static void log(Object log){
        System.out.println(log);
    }
}
class A {
    static {
        System.out.println("init "+A.class);
    }
    public static int ID = 1;
}

class B extends A{
    static {
        System.out.println("init "+B.class);
    }
}
