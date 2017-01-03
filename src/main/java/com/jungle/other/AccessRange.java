package com.jungle.other;

/**
 * Created by yunjiang on 2017/1/3.
 */
 public class AccessRange {
    void print(){
        System.out.println(this.getClass());
    }

    protected class A {
        void print(){
            System.out.println(this.getClass());
        }
    }

    private class B {
        void print(){
            System.out.println(this.getClass());
        }
    }

    class C {
        void print(){
            System.out.println(this.getClass());
        }
    }
}

class AccEx extends AccessRange {
}

class MainClass{
    public static void main(String[] args){
        AccessRange accessRange = new AccEx();
        AccEx accEx = new AccEx();
        accessRange.print();
        accessRange.new A().print();
        accEx.new C().print();
    }
}
