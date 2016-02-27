package 操作数组;

import java.lang.reflect.Array;

public class ArrayTest2 {

	/**
	 * 三维数组也是一纬数组，是数组元素是二维数组的一维数组，因此可以 认为arr是长度为3的一维数组
	 */
	public static void main(String[] args) throws Exception {
		Object arr = Array.newInstance(String.class, 3, 4, 10);
		// 获取arr数组中index为2的元素，应该是二维数组
		Object arrObj = Array.get(arr, 2);
		Array.set(arrObj, 2, new String[] { "a", "b" });
		Object anObj = Array.get(arrObj, 3);
		Array.set(anObj, 8, "ccccc");
		// 将arr强制类型转换为三维数组
		String[][][] cast = (String[][][]) arr;
		// 输出三维数组中指定元素的值
		System.out.println(cast[2][3][8]);
		System.out.println(cast[2][2][0]);
		System.out.println(cast[2][2][1]);

	}

}
