package 门面模式;

public class PaymentImpl implements Payment {
	// 模拟顾客支付费用的方法
	@Override
	public String pay() {
		String food = "快餐";
		System.out.println("你已经向收银员支付费用，你购买的食物是:" + food);
		return food;
	}

}
