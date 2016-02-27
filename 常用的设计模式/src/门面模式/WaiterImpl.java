package 门面模式;

public class WaiterImpl implements Waiter {

	@Override
	public void serve(String food) {
		System.out.println("服务员已将:" + food + "端过来");
	}

}
