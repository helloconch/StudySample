package 工厂方法和抽象工厂;

public class OutputFactoryFactory {
	// 定义一个方法用于返回输出设备
	public static OutputFactory getOutputFactory(String type) {
		if (type.equalsIgnoreCase("better"))
			return new BetterPrinterFactory();
		else
			return new PrinterFactory();
	}
}
