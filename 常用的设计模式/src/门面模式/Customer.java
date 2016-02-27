package 门面模式;

public class Customer {

	public static void main(String[] args) {
		Customer c = new Customer();
		c.haveDinner();
	}

	private void haveDinner() {
		Payment pay = new PaymentImpl();

		Cook cook = new CookImpl();

		Waiter waiter = new WaiterImpl();

		String food = pay.pay();
		food = cook.cook(food);
		waiter.serve(food);
		Facade f = new Facade();
		f.serveFood();
	}

}
