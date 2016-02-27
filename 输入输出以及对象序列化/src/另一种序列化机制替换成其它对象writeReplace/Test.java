package 另一种序列化机制替换成其它对象writeReplace;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		oos = new ObjectOutputStream(new FileOutputStream("replace.txt"));
		Person per = new Person("che", 1);
		oos.writeObject(per);
		ois = new ObjectInputStream(new FileInputStream("replace.txt"));
		ArrayList list = (ArrayList) ois.readObject();
		System.out.println(list);
		if (oos != null)
			oos.close();
		if (ois != null)
			ois.close();
	}

}
