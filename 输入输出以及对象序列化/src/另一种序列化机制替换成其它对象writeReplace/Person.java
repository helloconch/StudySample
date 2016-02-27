package 另一种序列化机制替换成其它对象writeReplace;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// 程序在序列化该对象前，先调用该方法，最后调用writeObject方法
	private Object writeReplace() throws Exception {
		System.out.println("writeReplace()方法进入");
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(name);
		list.add(age);
		return list;
	}
}
