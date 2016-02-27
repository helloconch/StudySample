package 门面模式;

public class Facade {
	// 定义被Facade封装的三个部门
	Payment pay;
	Cook cook;
	Waiter waiter;

	public Facade() {
		this.pay = new PaymentImpl();
		this.cook = new CookImpl();
		this.waiter = new WaiterImpl();
	}

	public void serveFood() {
		String food = pay.pay();
		food = cook.cook(food);
		waiter.serve(food);
	}

}
