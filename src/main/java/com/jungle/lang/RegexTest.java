package com.jungle.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/2/9 0009.
 */
public class RegexTest {
    public static void main(String[] args){
        String reg = "([a-z0-9]*)";
        //reg = "(a)";
        String source = "$是的 Aa".toLowerCase();
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(source);
        //System.out.println(matcher.find());
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
