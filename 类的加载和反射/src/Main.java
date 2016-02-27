import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class Main {

	/**
	 * 类加载 类的加载器由类加载器完成，类加载器通常由JVM提供，这些类加载器也是我们程序运行的基础，
	 * JVM提供的这些类加载器通常被称为系统类加载器，除此之外，开发者可以通过继承ClassLoader基类 来创建自己的类加载器
	 * 通常使用不同的类加载器，可以从不同来源加载类的二进制数据，通常有如下几种来源 1.从本地文件系统来加载class文件
	 * 2.从JAR包中加载class文件 3.通过网络加载class文件 4.把一个Java源文件动态编译，并执行加载
	 * 
	 * 
	 * 
	 * 类连接 当类加载之后，系统为之生成一个对应的Class对象，接着将会进入连接阶段，连接阶段将会负责把类的二进制数据
	 * 合并到JRE中，类连接又可分为如下三个阶段： 验证：验证阶段用于检测被加载的类是否有正确的内部结构，并和其他类协调一致。
	 * 准备：类准备阶段负责为类的静态属性分配内存，并设置默认初始值。 解析：将类的二进制数据中的符号引用替换成直接引用
	 * 
	 * 类初始化 在类的初始化阶段，虚拟机负责对类进行初始化，主要就是对静态属性进行初始化 在JAva类中对静态属性指定初始值有两种方式：
	 * 1）声明静态属性时指定初始值 2）使用静态初始化块为静态属性值指定初始值
	 * 
	 * JVM初始化一个类包含如下几个步骤: 1.假如这个类还没有被加载和连接，程序先加载并连接该类
	 * 2.假如该类的直接父类还没有被初始化，则先初始化其直接父类 3.假如类中有初始化语句，则系统依次执行这些初始化语句
	 * 
	 * 
	 * 类的初始化时机: 1.创建类的实例，为某个类创建实例的方式包括使用new操作符来创建实例，通过反射来创建 实例，通过反序列化的方式来创建实例
	 * 2.调用某个类的静态方法 3.访问某个类或借口的静态属性，或为该静态属性赋值。
	 * 4.使用反射方式来强制创建某个类或接口对应的java.lang.Class对象。例如代码：
	 * Class.forName("Person")，如果系统还未初始化Person类。
	 * 5.初始化某个类的子类，当初始化某个类的子类时，该子类的所有父类都会被初始化。
	 * 6.直接使用java.exe命令来运行某个主类，当运行某个主类时，程序会先初始化该主类。
	 * 
	 * 注意:当某个静态属性使用final修饰，而且它的值可以在编译时得到，那么程序其他地方
	 * 使用该静态属性时，实际上并不会使用该静态，而是相当于使用常量，类不会被初始化。
	 * 反之，如果final类型的静态属性值不能在编译时得到，必须等到运行时才可以确定该属性的值， 该类会被初始化。
	 * 
	 * 
	 * *****当使用ClassLoader类的loadClass()方法来加载某个类时，该方法只是加载该类，并不会
	 * 执行该类的初始化。当使用Class的forName（）静态方法才会导致强制初始化该类。
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * 
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		ClassLoader loader = ClassLoader.getSystemClassLoader();
		loader.loadClass("Tester");
		Class.forName("Tester");

		// 根类加载器非常特殊，它并不是java.lang.ClassLoader的子类，而是由JVM自身实现的
		URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		// 遍历、输出根类加载器加载的全部URL
		for (int i = 0; i < urLs.length; i++) {
			System.out.println(urLs[i].toExternalForm());
		}

		// 获取系统类加载器
		ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
		System.out.println("系统类加载器:" + systemLoader);
		/*
		 * 获取系统类加载器的加载路径---通常由CLASSPATH环境变量指定
		 * 如果操作系统没有指定CLASSPATH环境变量，默认以当前路径作为系统类加载器的加载路径
		 */

		Enumeration<URL> em1 = systemLoader.getResources("");
		while (em1.hasMoreElements()) {
			System.out.println(em1.nextElement());
		}
		// 获取系统类加载器的父类加载器-应该得到扩展类加载器
		ClassLoader extensionLoader = systemLoader.getParent();
		System.out.println("扩展类加载器:" + extensionLoader);
		System.out.println("扩展类加载器的加载路径:" + System.getProperty("java.ext.dirs"));
		System.out.println("扩展类加载器的parent:" + extensionLoader.getParent());

	}

}

class Tester {
	static {
		System.out.println("Tester类的静态初始块");
	}
}
