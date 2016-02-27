package 对象序列化;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadTeacher {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teacher.txt"));
		Teacher t1 = (Teacher) ois.readObject();
		Teacher t2 = (Teacher) ois.readObject();
		Person p = (Person) ois.readObject();
		Teacher t3 = (Teacher) ois.readObject();
		System.out.println("t1 对person引用和p是否是同一个:" + (t1.getPer() == p));
		System.out.println("t2 对person引用和p是否是同一个:" + (t2.getPer() == p));
		System.out.println("t2 和t3是否是同一个:" + (t2 == t3));
		if (ois != null)
			ois.close();
	}

}
