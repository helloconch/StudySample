package 工厂方法和抽象工厂;

public class BetterPrinterFactory implements OutputFactory {

	@Override
	public Output getOutput() {
		return new BetterPrinter();
	}

}
