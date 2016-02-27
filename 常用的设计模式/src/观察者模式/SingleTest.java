package 观察者模式;
public class SingleTest {
	private static SingleTest instance;

	private SingleTest() {
	}

	public static SingleTest getInstance() {
		if (instance == null) {
			instance = new SingleTest();
		}
		return instance;

	}
}
