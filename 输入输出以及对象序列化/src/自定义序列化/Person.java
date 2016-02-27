package 自定义序列化;

import java.io.Serializable;

public class Person implements Serializable {

	private String name;
	// java序列化时无须理会该值
	private transient int age;

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

}
