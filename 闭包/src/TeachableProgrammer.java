public class TeachableProgrammer extends Programmer implements Teachable {
	// 产生闭包现象
	public void P() {
		work();
	}

	public static void main(String[] args) {
		TeachableProgrammer t = new TeachableProgrammer();
		t.P();

	}

}
