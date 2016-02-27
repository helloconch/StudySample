package 简单工厂;

public class OutputFactory {

	public Output getOutput() {
		// 用于控制系统到底使用Output的那个实现类
		return new Printer();
	}
}
