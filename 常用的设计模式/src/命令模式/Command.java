package 命令模式;

public interface Command {
	// 接口里的process方法用于封装"处理行为"
	void process(int[] target);
}
