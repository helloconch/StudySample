package 观察者模式;
public class NameObserver implements Observer {

	@Override
	public void update(Observeable o, Object arg) {
		if (arg instanceof String) {
			System.out.println("观察者:" + o + "---" + "物品名称已经改变:" + arg);
		}
	}

}
