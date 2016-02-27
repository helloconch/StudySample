package 操作数组;

import java.lang.reflect.Array;

public class ArrayTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建一个元素类型为String 长度为10的数组
		Object arr = Array.newInstance(String.class, 10);
		// 依次为arr数组中index为5,6的元素赋值
		Array.set(arr, 5, "a");
		Array.set(arr, 6, "b");

		Object object = Array.get(arr, 5);
		Object object2 = Array.get(arr, 6);
		System.out.println(object);
		System.out.println(object2);

	}

}
