package 另一种序列化机制;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("other.txt"));
		Person p = new Person("che", 10);
		p.writeObject(oos);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("other.txt"));
		p.readObject(ois);
		if (ois != null)
			ois.close();
		if (oos != null)
			oos.close();
	}

}
