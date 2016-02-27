package 另一种序列化机制Resolve;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Orientation implements Serializable {

	public static final Orientation HORIZONTAL = new Orientation(1);
	public static final Orientation VERTICAL = new Orientation(2);
	private int value;

	private Orientation(int value) {
		this.value = value;
	}

	// 这个方法会紧接着readObject之后被调用，该方法的返回值将会代替原来反序列化的对象
	// 而原来readObject反序列化的对象将会被立即丢弃
	private Object readResolve() throws ObjectStreamException {
		System.out.println("readResolve()");
		if (value == 1)
			return HORIZONTAL;
		if (value == 2)
			return VERTICAL;
		return null;
	}
}
