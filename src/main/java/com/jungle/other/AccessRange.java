package com.jungle.other;

import com.jungle.lang.AccRange;

/**
 * Created by yunjiang on 2017/1/3.
 * test public protected default private.
 */
 public class AccessRange {
    protected void print(){
        System.out.println(this.getClass());
    }

    void printDefult(){
        System.out.println("AccessRange.printDefult "+this.getClass());
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
    @Override
    public void print(){
        System.out.println("AccEx.print "+this.getClass());
    }
}

class MainClass{
    public static void main(String[] args){
        AccessRange accessRange = new AccEx();
        AccEx accEx = new AccEx();
        accessRange.print();
        accessRange.new A().print();
        accEx.new C().print();
        AccessRange accessRange1 = new AccRange();
        accessRange1.printDefult();
        new AccRange().printDefult();
    }
}
