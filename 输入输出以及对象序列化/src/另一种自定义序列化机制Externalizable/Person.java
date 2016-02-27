package 另一种自定义序列化机制Externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

//方法、static属性、transient属性都不会被序列化
public class Person implements Externalizable {

	private static final long serialVersionUID = 70L;
	private String name;
	private transient int age;

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.name = ((StringBuffer) in.readObject()).reverse().toString();
		this.age = in.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(new StringBuffer(name).reverse());
		out.writeInt(age);

	}

}
