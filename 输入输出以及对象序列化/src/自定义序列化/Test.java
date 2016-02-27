package 自定义序列化;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("transient.txt"));
		Person per = new Person("che", 12);
		oos.writeObject(per);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("transient.txt"));
		Person p = (Person) ois.readObject();
		System.out.println(p.getAge());
		if (ois != null)
			ois.close();
		if (oos != null)
			oos.close();
	}

}
