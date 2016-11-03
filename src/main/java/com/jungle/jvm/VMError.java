package com.jungle.jvm;

import java.util.ArrayList;
import java.util.List;

public class VMError {
	public static int stackDepth=0;
	public static void main(String[] args) {
		//addObj();
		try {
			stackOverflow();
		} catch (Throwable e) {
			// TODO: handle exception			
			System.out.println(stackDepth);
		}
		
	}
	
	/**
	 * VM args -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
	 */
	public static void addObj() {
		List<Object> objects = new ArrayList<>();
		while (true) {
			objects.add(new Object());
		}
	}
	
	public static void stackOverflow(){
		stackDepth++;
		stackOverflow();
	}

}
