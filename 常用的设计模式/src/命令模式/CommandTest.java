package 命令模式;

public class CommandTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProcessArray pa = new ProcessArray();
		int[] target = { 3, -4, 6, 4 };
		// 第一次处理数组,具体处理行为取决于Command对象
		pa.each(target, new Command() {

			@Override
			public void process(int[] target) {
				for (int tmp : target) {
					System.out.println("元素分别为:" + tmp);
				}

			}
		});

		// 第二次处理数组，具体处理行为取决于Command对象

		pa.each(target, new Command() {

			@Override
			public void process(int[] target) {
				int sum = 0;
				for (int tmp : target) {
					sum += tmp;
				}
				System.out.println("元素总和:" + sum);
			}
		});

	}

}
