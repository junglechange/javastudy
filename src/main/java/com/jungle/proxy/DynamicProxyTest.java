package com.jungle.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/12/25 0025.
 */
public class DynamicProxyTest {
    interface IHello{
        void sayH();
        void sayB();
    }
    static class MyHello implements IHello{
      public void sayH(){
            System.out.println("hello");
        }

        public void sayB(){
            System.out.println("bye");
        }
    }

    static class DynamicProxy implements InvocationHandler{
        Object oriObj;
       public Object bind(Object oriObj){
           this.oriObj = oriObj;
            return  Proxy.newProxyInstance(oriObj.getClass().getClassLoader(),oriObj.getClass().getInterfaces(),this);
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
            return method.invoke(oriObj,args);
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println(System.getProperties());
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello hello = (IHello) new DynamicProxy().bind(new MyHello());
        hello.sayH();
        hello.sayB();
        Class myHelloClazz = IHello.class;
        Method sayHello = myHelloClazz.getMethod("sayH",null);
        sayHello.invoke(new MyHello(),null);
    }
}
