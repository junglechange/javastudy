package com.jungle.jvm;

import java.util.ArrayList;
import java.util.List;

public class VMError {
	public static int stackDepth=0;
	public static void main(String[] args) {
		System.out.println("hello");
		//addObj();
		try {
			//stackOverflow();
			//multiThreadStackOverflow();
		} catch (Throwable e) {
			// TODO: handle exception			
			System.out.println(stackDepth);
		}
		
	}
	
	/**
	 * VM args -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
	 */
	public static void addObj() {
		List<Object> objects = new ArrayList<Object>();
		while (true) {
			objects.add(new Object());
		}
	}
	
	/**
	 * VM args -Xss128k
	 * 增加局部变量会增加每个方法调用的栈帧的size，所以溢出的时候，调用栈的深度减少。
	 * The stack frame, also known as activation record is the collection of all data on the stack associated with one subprogram call.
	 * 
	 */
	public static void stackOverflow(){
		stackDepth++;
	
		long tmp=1;
		long tmp1=2;
		long tmp3=1;
		long tmp4=2;
		

		stackOverflow();
	}
	
	/**
	 * VM args -Xss128k
	 * 可能会死机
	 */
	public static void multiThreadStackOverflow() {
		while (true) {
			new Thread(new T1()).start();
			stackDepth++;
		}
		
	}

}


	class T1 implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
	
			}
			
		}
		
	
	
	
}
