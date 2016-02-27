package 工厂方法和抽象工厂;

public class PrinterFactory implements OutputFactory {

	@Override
	public Output getOutput() {
		// TODO Auto-generated method stub
		return new Printer();
	}

}
