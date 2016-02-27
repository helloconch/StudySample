package 通过反射查看类信息;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@SuppressWarnings(value = "unchecked")
@Deprecated
public class ClassTest {
	private ClassTest() {
	}

	public ClassTest(String name) {
		System.out.println("执行有参数的构造器");
	}

	public void info() {
		System.out.println("定义无参数的info方法");
	}

	public void info(String str) {
		System.out.println("定义有参数的info方法，参数值:" + str);
	}

	// 定义一个测试用的内部类
	class Inner {

	}

	public static void main(String[] args) throws Exception {
		// 下面代码可以获取ClassTest对应的class
		Class<ClassTest> clazz = ClassTest.class;
		// 获取该Class对象所对应类的全部构造器
		Constructor[] ctors = clazz.getDeclaredConstructors();
		System.out.println("ClassTest的全部构造器如下:");
		for (Constructor c : ctors) {
			System.out.println(c);
		}

		// 获取该Class对象所对应类的全部public构造器
		Constructor[] constructors = clazz.getConstructors();
		System.out.println("ClassTest对象所对应类的全部public构造器");
		for (Constructor c : constructors) {
			System.out.println(c);
		}
		// 获取该Class对象所对应类的全部public方法
		Method[] methods = clazz.getMethods();
		System.out.println("ClassTest的全部public方法如下：");
		for (Method m : methods) {
			System.out.println(m);
		}
		// 获取该Class对象所对应类的指定方法
		System.out.println("ClassTest里带一个字符串参数的info方法为:" + clazz.getMethod("info", String.class));

		// 获取Class对象所对应类的全部注释
		java.lang.annotation.Annotation[] annotations = clazz.getAnnotations();
		System.out.println("ClassTest的全部Annotation:");
		for (Annotation a : annotations) {
			System.out.println(a);
		}
		System.out.println("该Class元素上的@SuppressWarnings注释为:" + clazz.getAnnotation(SuppressWarnings.class));

		// 获取该Class对象所对应类的全部内部类
		Class<?>[] inners = clazz.getDeclaredClasses();
		System.out.println("ClassTest的全部内部类如下：");
		for (Class c : inners) {
			System.out.println(c);
		}
		// 使用Class.forName方法加载ClassTest的Inner内部类
		Class inClazz = Class.forName("通过反射查看类信息.ClassTest$Inner");
		// 通过getDeclaringClass()访问该类所在的外部类
		System.out.println("inClazz对应的外部类为:" + inClazz.getDeclaringClass());
		System.out.println("ClassTest的包为:" + clazz.getPackage());
		System.out.println("ClassTest的父类为:" + clazz.getSuperclass());
	}
}
