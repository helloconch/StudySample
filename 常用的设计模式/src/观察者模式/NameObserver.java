package �۲���ģʽ;
public class NameObserver implements Observer {

	@Override
	public void update(Observeable o, Object arg) {
		if (arg instanceof String) {
			System.out.println("�۲���:" + o + "---" + "��Ʒ�����Ѿ��ı�:" + arg);
		}
	}

}
