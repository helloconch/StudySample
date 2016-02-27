package 桥接模式;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//得到辣味牛肉面
		AbstractNoodle noodle1=new BeefNoodle(new PepperyStyle());
		noodle1.eat();
		//得到不是辣味牛肉面
		AbstractNoodle noodle2=new BeefNoodle(new PlainStyle());
		noodle2.eat();
		//得到辣味猪肉面
		AbstractNoodle noodle3=new PorkyNoodle(new PepperyStyle());
		noodle3.eat();
		//得到不是辣味猪肉面
		AbstractNoodle noodle4=new PorkyNoodle(new PlainStyle());
		noodle4.eat();
	}

}
