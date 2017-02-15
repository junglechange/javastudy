package com.jungle.other;

import com.jungle.concurrent.InterruptTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/2/12 0012.
 */
public class Mess {
    public static void main(String[] args){
        String source = "27548";
        int a = 2;
        int b = -1;
        int c;
        c = a/b;
        System.out.println(test());
        TestException testException1 = new TestException();
        try {
            testException1.testEx();
        } catch (Exception e) {
            //e.printStackTrace();

            System.out.println(e.getStackTrace());
        }
        //System.out.println(strToInt(source));

    }

    public static String test(){
        String name = "123";
        try {
            throw new Exception();
//return name;
        } catch (Exception e) {
            return name;
        }finally{
            name = "456";
        }
    }

    public static Integer strToInt(String data){
        Integer num = null;
        if(data!=null&&!data.equalsIgnoreCase("")){
            Pattern pattern = Pattern.compile("^[0-9]+$");
            Matcher matcher = pattern.matcher(data);
            if (matcher.find()){
                num = 0;
                for(int index=0;index<data.length();index++){
                    char tmpNumChar = data.charAt(index);
                    num = num*10 + (tmpNumChar - '0');
                }
            }
        }
        return num;
    }


}
class TestException {
    public TestException() {
    }

    boolean testEx() throws Exception {
        boolean ret = true;
        try {
            ret = testEx1();
System.out.println("testEx ret:"+ret);
            return ret;
        } catch (Exception e) {
            System.out.println("testEx, catch exception");
            ret = false;
            throw new Exception(e.getCause());
        } finally {
            System.out.println("testEx, finally; return value=" + ret);
            //return ret;
        }
        //return ret;
    }

    boolean testEx1() throws Exception {
        boolean ret = true;
        try {
            ret = testEx2();
//            if (!ret) {
//                return false;
//            }
            //ret = false;
            //System.out.println("testEx1, at the end of try");
            System.out.println("testEx1 ret:"+ret);
            return ret;
        } catch (Exception e) {
            System.out.println("testEx1, catch exception");
            ret = false;
            throw new Exception(e);
        } finally {
            System.out.println("testEx1, finally; return value=" + ret);
            //return ret;
        }
        //return ret;
    }

    boolean testEx2() throws Exception {
        boolean ret = true;
        try {
            int b = 12;
            int c;
            for (int i = 2; i >= -2; i--) {
                c = b / i;
                System.out.println("i=" + i);
            }
            return true;
        } catch (Exception e) {
            System.out.println("testEx2, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx2, finally; return value=" + ret);
            //return ret;
        }
    }
}

