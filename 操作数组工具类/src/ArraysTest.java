import java.util.Arrays;

public class ArraysTest {
	public static void main(String[] args) {
		// 定义一个a数组
		int[] a = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		// 定义一个b数组
		int[] b = new int[] { 1, 2, 3, 4, 5, 6, 7, 0 };
		// a b 数组元素是否相等
		System.out.println("a b 数组元素是否相等" + Arrays.equals(a, b));
		// 复制a数组 生成c数组
		int[] c = Arrays.copyOf(a, 6);
		System.out.println("复制a数组 生成c数组" + Arrays.toString(c));
		// 将b数组第3个元素(包括)到第5个元素(不包括)赋值1
		Arrays.fill(b, 2, 4, 1);
		System.out.println("将b数组第3个元素(包括)到第5个元素(不包括)赋值1" + Arrays.toString(b));
		// 对b数组进行排序
		Arrays.sort(b);
		System.out.println("对b数组进行排序" + Arrays.toString(b));

	}

}
