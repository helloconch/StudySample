package 另一种序列化机制;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable {
	private String name;
	private transient int age;

	public Person(String name, int age) {
		super();
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

	protected void writeObject(ObjectOutputStream out) throws Exception {
		out.writeObject(new StringBuffer(name).reverse());
		out.writeInt(age);
	}

	protected void readObject(ObjectInputStream in) throws Exception {
		this.name = ((StringBuffer) in.readObject()).reverse().toString();
		// this.age = in.readInt();
		System.out.println("name:" + name);
	}

}
