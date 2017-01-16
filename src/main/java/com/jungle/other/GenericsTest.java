package com.jungle.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/26 0026.
 */
public class GenericsTest {
 public static void main(String[] args){
     List<String> strings = new ArrayList<String>();
     strings.add("sd");
     strings.add("ab");
     String allStr = org.apache.commons.lang3.StringUtils.join(strings,",");
     System.out.println(strings);
     System.out.println(allStr);
 }
}
