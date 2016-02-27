package 门面模式;

public class CookImpl implements Cook {

	@Override
	public String cook(String food) {
		System.out.println("厨师正在烹调:" + food);
		return food;
	}

}
