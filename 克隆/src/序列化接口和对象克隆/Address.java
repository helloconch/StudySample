package 序列化接口和对象克隆;

import java.io.Serializable;

public class Address implements Serializable {
	public Address() {
	}

	public Address(String state, int number) {
		this.number = number;
		this.state = state;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("number: " + number);
		sb.append("state" + state + "\n");
		return sb.toString();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	private String state;
	private int number;
}