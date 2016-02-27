public class TeachableProgrammer2 extends Programmer {
	// 伪闭包
	public TeachableProgrammer2() {
	}

	public TeachableProgrammer2(String name) {
		super(name);
	}

	private void teach() {
		System.out.println(getName() + "---在讲课");
	}

	private class Closure implements Teachable {

		@Override
		public void work() {
			teach();

		}

	}

	// 返回一个非静态内部类引用,允许外部类通过该非静态内部类引用来回调外部类的方法
	public Teachable getCaTeachable() {
		return new Closure();
	}
}
