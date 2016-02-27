package 对象序列化;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// 程序可以通过以下方式对对象进行序列化
		ObjectOutputStream oos = null;

		// 创建一个ObjectOutputStream输出流
		oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
		Person per=new Person("che",10);
		//将per对象写入输出流
		oos.writeObject(per);
		if(oos!=null)
			oos.close();
	}

}
