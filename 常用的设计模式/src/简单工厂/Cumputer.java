package 简单工厂;

public class Cumputer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OutputFactory of = new OutputFactory();
		Cumputer c = new Cumputer(of.getOutput());
		c.keyIn("123");
		c.print();
	}

	private Output out;

	public Cumputer(Output out) {
		this.out = out;
	}

	// 定义一个模拟获取字符串输入的方法
	public void keyIn(String msg) {
		out.getData();
	}

	// 定义一个模拟打印的方法
	public void print() {
		out.out();
	}

}
