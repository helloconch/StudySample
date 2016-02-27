package 命令模式;

public class ProcessArray {
	// 定义一个each()方法，用于处理数组
	public void each(int[] target, Command cmd) {
		cmd.process(target);
	}

}
