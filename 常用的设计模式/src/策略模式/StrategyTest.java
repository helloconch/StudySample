package 策略模式;

public class StrategyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 客户端没有选择打折策略类
		DiscountContext dc = new DiscountContext(null);
		double price = 79;
		// 使用默认的打折策略
		System.out.println("---" + dc.getDiscountPrice(price));
		dc.changeDiscount(new VipDiscount());
		double price2 = 89;
		System.out.println("------" + dc.getDiscountPrice(price2));
	}

}
