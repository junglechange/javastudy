package com.jungle.other;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class JsonDeserialize {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Map<Object, String> map1 = new HashMap<Object, String>(4,0.75f);
		map1.put(1, "2");
		map1.put("2", "3");
		map1.put("3", "3");
		map1.put("4", "3");
		map1.put("5", "3");
		map1.put("6", "3");
		map1.put("7", "3");
		map1.put("8", "3");
		map1.put("9", "3");
		map1.put("10", "3");
		System.out.println(map1.get(new Integer(1)));
		People people = new People(21);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("p1.o")));
		objectOutputStream.writeObject(people);
		ObjectInput objectInput = new ObjectInputStream(new FileInputStream(new File("p1.o")));
		People p2 =(People)objectInput.readObject();
		System.out.println(p2);
		System.out.println(1 << 30);

	}
}

class People implements Serializable{
	public static final int serialVersionUID =1;
	int a = 0;
	public People(int a){
		this.a = a;
	}

	public String toString(){
		return String.valueOf(a);
	}
}
