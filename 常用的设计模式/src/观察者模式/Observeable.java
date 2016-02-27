package 观察者模式;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Observeable {
	// ��һ��List4����ö��������а󶨵��¼�������
	List<Observer> observers = new ArrayList<Observer>();

	// ����һ��������ڴӸ�������ע��۲���
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	// ֪ͨ��������ע������й۲���
	public void notifyObservers(Object value) {
		for (Iterator it = observers.iterator(); it.hasNext();) {
			Observer o = (Observer) it.next();
			o.update(this, value);
		}
	}
}
