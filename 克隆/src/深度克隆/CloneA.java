package 深度克隆;

public class CloneA implements Cloneable {

	private int i;

	public CloneA(int ii) {
		i = ii;
	}

	public void doubleValue() {
		i *= 2;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		CloneA a = null;
		try {
			a = (CloneA) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public String toString() {
		return Integer.toString(i);
	}

}
