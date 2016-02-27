package 序列化接口和对象克隆;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Employee implements Cloneable, Serializable {
	private String name;
	private int age;
	private Address address;

	public Employee() {
	}

	public Employee(String name, int age, Address address) {
		this.address = address;
		this.age = age;
		this.name = name;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name:" + name + ", ");
		sb.append("age:" + age + " \n");
		sb.append("Address: " + address);
		return sb.toString();
	}

	@Override
	protected Employee clone() {
		Employee employee = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			oos.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			employee = (Employee) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
}
