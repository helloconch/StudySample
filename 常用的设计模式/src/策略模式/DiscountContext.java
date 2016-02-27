package 策略模式;

public class DiscountContext {

	// 组合一个DiscountStrategy对象
	private DiscountStrategy strategy;

	public DiscountContext(DiscountStrategy strategy) {
		this.strategy = strategy;
	}
	//根据实际所使用的DiscountStrategy对象得到折扣价
	public double getDiscountPrice(double price){
		if(strategy==null)
			strategy=new OldDiscount();
		return this.strategy.getDiscount(price);
	}
	
	//提供切换算法的方法
	public void changeDiscount(DiscountStrategy strategy){
		this.strategy = strategy;
	}

}
