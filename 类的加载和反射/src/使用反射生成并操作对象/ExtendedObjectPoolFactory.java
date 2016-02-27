package 使用反射生成并操作对象;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;

public class ExtendedObjectPoolFactory {

	/**
	 * 通过反射生成对象两种方式： 1.使用Class对象的newInstance（）方法来创建该Class对象对应类的实例
	 * 这种方式要求该Class对象的对应类有默认的构造器，而执行newInstance（）方法时 实际上是利用默认构造器来创建该类的实例
	 * 2.先使用Class对象获取指定的Construct对象，再调用Construct对象的newInstance（）
	 * 方法来创建该Class对象对应的实例。通过这种方式可以选择使用某个类指定构造器来创建实例。
	 */

	// 定义一个对象池，前面是对象名，后面是实际对象
	private Map<String, Object> objectPool = new HashMap<String, Object>();
	private Properties config = new Properties();

	// 从指定属性文件中初始化Properties对象
	public void init(String fileName) throws Exception {
		FileInputStream fils = null;
		try {
			fils = new FileInputStream(fileName);
			config.load(fils);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fils != null)
				fils.close();
		}
	}

	// 定义一个创建对象的方法，该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
	private Object createObject(String clazzName) throws Exception {
		// 根据字符串来获取对应的Class对象
		Class<?> clazz = Class.forName(clazzName);
		// 使用clazz对应类的默认构造器创建实例
		return clazz.newInstance();
	}

	// 该方法根据指定文件来初始化对象池，它会根据配置文件来创建对象
	public void initPool() throws Exception {
		for (String name : config.stringPropertyNames()) {
			if (!name.contains("%")) {
				objectPool.put(name, createObject(config.getProperty(name)));
			}
		}
	}

	// 该方法根据指定文件来初始化对象池
	// 它会根据配置文件来创建对象
	public void initProperty() throws Exception {
		for (String name : config.stringPropertyNames()) {
			if (name.contains("%")) {
				String[] objAndProp = name.split("%");
				Object target = getObject(objAndProp[0]);
				// 该属性对应的setter方法名:set+"属性的首字母大写"+剩下部分
				String mtdName = "set" + objAndProp[1].substring(0, 1).toUpperCase() + objAndProp[1].substring(1).toLowerCase();
				// 通过target的getClass()获取实现类所对应的Class对象
				Class<? extends Object> targetClass = target.getClass();
				// 获取该属性对应的setter方法
				Method mtd = targetClass.getMethod(mtdName, String.class);
				// 取消对Java语言访问权限
				mtd.setAccessible(true);
				// 通过Method的invoke方法执行setter方法
				// 将config.getProperty(name)的属性值作为调用setter方法的实参
				mtd.invoke(target, config.getProperty(name));
			}
		}
	}

	public Object getObject(String name) {
		return objectPool.get(name);
	}

	public static void main(String[] args) throws Exception {
		ExtendedObjectPoolFactory pf = new ExtendedObjectPoolFactory();
		pf.init("extObj.txt");
		pf.initPool();
		pf.initProperty();
		System.out.println(pf.getObject("a"));
	}

}
