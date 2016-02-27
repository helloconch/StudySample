public class Account {
	// 定义一个ThreadLocal类型变量,该变量将是一个线程局部变量
	private ThreadLocal<String> name = new ThreadLocal<String>();

	public Account(String str) {
		this.name.set(str);
		System.out.println("----------" + this.name.get());
	}

	public String getName() {
		return this.name.get();

	}

	public void setName(String str) {
		this.name.set(str);
	}

	public static void main(String[] args) {
		Account at = new Account("初始名");
		new MyTest(at, "A").start();
		new MyTest(at, "B").start();
	}
}
