package com.jungle.other;

import java.util.HashMap;
import java.util.Map;

public class JsonDeserialize {

	public static void main(String[] args){
		Map<Object, String> map1 = new HashMap<Object, String>();
		map1.put(1, "2");
		map1.put("2", "3");
		System.out.println(map1.get(new Integer(1)));
	}
}
