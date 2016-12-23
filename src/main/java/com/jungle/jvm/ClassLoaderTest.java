package com.jungle.jvm;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created by yunjiang on 2016/12/23.
 */
public class ClassLoaderTest {

    class MyClassLoader extends ClassLoader{
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException{
            try{
                String fileName = name.substring(name.lastIndexOf('.')+1)+".class";
                InputStream ins = MyClassLoader.class.getResourceAsStream(fileName);
                if(ins == null){
                    return super.loadClass(name);
                }
                byte[] b = new byte[ins.available()];
                ins.read(b);
                Class<?> clazz = defineClass(name,b,0,b.length);
                return  clazz;
            }catch (Exception e){
                throw new ClassNotFoundException(name);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println(Thread.currentThread().getContextClassLoader());
        ClassLoaderTest classLoaderTest = new ClassLoaderTest();
        MyClassLoader myClassLoader = classLoaderTest.new MyClassLoader();
        Object obj = myClassLoader.loadClass("com.jungle.jvm.ClassLoaderTest").newInstance();
        System.out.println(obj instanceof ClassLoaderTest);

    }
}
