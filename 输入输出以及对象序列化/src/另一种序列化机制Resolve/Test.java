package 另一种序列化机制Resolve;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

	/**
	 * readResolve方法在序列化单例类、枚举类时尤其有用<br/>
	 * 这样能保证反序列化的对象依然正常
	 */
	public static void main(String[] args) throws Exception {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		oos = new ObjectOutputStream(new FileOutputStream("resolve.txt"));
		ois = new ObjectInputStream(new FileInputStream("resolve.txt"));
		oos.writeObject(Orientation.HORIZONTAL);
		Orientation ori = (Orientation) ois.readObject();
		System.out.println(ori == Orientation.HORIZONTAL);
		if (oos != null)
			oos.close();
		if (ois != null)
			ois.close();

	}

}
