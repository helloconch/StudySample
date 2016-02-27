package 对象序列化;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadObject {

	/**
	 * 从object.txt文件中读取Person对象
	 */
	public static void main(String[] args) throws Exception {
		ObjectInputStream ois = null;
		ois = new ObjectInputStream(new FileInputStream("object.txt"));
		Person p = (Person) ois.readObject();
		System.out.println("名字:" + p.getName() + " age:" + p.getAge());
		if (ois != null)
			ois.close();
	}

}
