package com.jungle.lang;
import com.jungle.other.*;

/**
 * Created by yunjiang on 2017/1/3.
 */
public class AccRange  extends AccessRange{
    //@Override error  printDefult's modifier is default,so class outside the package com.jungle.other can not access this method
    public void printDefult(){
        System.out.println("AccRange.printDefult "+this.getClass());
    }
    public static void main(String[] args){

    }
}
