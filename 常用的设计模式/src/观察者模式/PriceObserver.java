package 观察者模式;
public class PriceObserver implements Observer {

	@Override
	public void update(Observeable o, Object arg) {
		if (arg instanceof Double) {
			System.out.println("�۲���:" + o + "---" + "��Ʒ�۸��Ѿ��ı�:" + arg);
		}
	}

}
