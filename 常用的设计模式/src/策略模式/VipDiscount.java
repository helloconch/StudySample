package 策略模式;

public class VipDiscount implements DiscountStrategy {

	@Override
	public double getDiscount(double originPrice) {
		System.out.println("使用ViP打折算法 ");
		return originPrice * 0.5;
	}

}
