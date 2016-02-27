package 深度克隆;

public class CloneB implements Cloneable {
	public int aInt;
	// 引用类型
	public CloneA a = new CloneA(111);

	@Override
	protected Object clone() throws CloneNotSupportedException {
		CloneB b = null;
		try {
			b = (CloneB) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		b.a = (CloneA) a.clone();
		return b;
	}

}
