package 策略模式;

public class OldDiscount implements DiscountStrategy {

	@Override
	public double getDiscount(double originPrice) {
		System.out.println("使用旧书打折 ");
		return originPrice * 0.7;
	}

}
