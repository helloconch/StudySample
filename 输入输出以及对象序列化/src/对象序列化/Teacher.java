package 对象序列化;

import java.io.Serializable;

public class Teacher implements Serializable {
	private String name;
	private Person per;

	public Teacher(String name, Person per) {
		this.name = name;
		this.per = per;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getPer() {
		return per;
	}

	public void setPer(Person per) {
		this.per = per;
	}

}
