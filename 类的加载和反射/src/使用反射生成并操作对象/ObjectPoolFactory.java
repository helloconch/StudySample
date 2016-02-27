package 使用反射生成并操作对象;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObjectPoolFactory {

	/**
	 * 通过反射生成对象两种方式： 1.使用Class对象的newInstance（）方法来创建该Class对象对应类的实例
	 * 这种方式要求该Class对象的对应类有默认的构造器，而执行newInstance（）方法时 实际上是利用默认构造器来创建该类的实例
	 * 2.先使用Class对象获取指定的Construct对象，再调用Construct对象的newInstance（）
	 * 方法来创建该Class对象对应的实例。通过这种方式可以选择使用某个类指定构造器来创建实例。
	 */

	// 定义一个对象池，前面是对象名，后面是实际对象
	private Map<String, Object> objectPool = new HashMap<String, Object>();

	// 定义一个创建对象的方法，该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
	private Object createObject(String clazzName) throws Exception {
		// 根据字符串来获取对应的Class对象
		Class<?> clazz = Class.forName(clazzName);
		// 使用clazz对应类的默认构造器创建实例
		return clazz.newInstance();
	}

	// 该方法根据指定文件来初始化对象池，它会根据配置文件来创建对象
	public void initPool(String fileName) throws Exception {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			// fis = this.getClass().getResourceAsStream("/obj.txt");
			Properties props = new Properties();
			props.load(fis);
			for (String name : props.stringPropertyNames()) {
				// 每次取出属性名-属性值，就根据属性值创建一个对象
				objectPool.put(name, createObject(props.getProperty(name)));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}
	}

	public Object getObject(String name) {
		return objectPool.get(name);
	}

	public static void main(String[] args) throws Exception {
		ObjectPoolFactory pf = new ObjectPoolFactory();
		pf.initPool("obj.txt");
		System.out.println(pf.getObject("a"));
	}

}
