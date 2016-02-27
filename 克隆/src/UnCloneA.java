public class UnCloneA {
	private int i;

	public UnCloneA(int ii) {
		i = ii;
	}

	public void doubleValue() {
		i *= 2;
	}

	public String toString() {
		return Integer.toString(i);
	}
}
