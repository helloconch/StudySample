package 对象序列化;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class WriterTeacher {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teacher.txt"));
		Person per = new Person("che", 10);
		Teacher t1 = new Teacher("t1", per);
		Teacher t2 = new Teacher("t2", per);
		oos.writeObject(t1);
		oos.writeObject(t2);
		oos.writeObject(per);
		oos.writeObject(t2);
		if (oos != null)
			oos.close();

	}

}
