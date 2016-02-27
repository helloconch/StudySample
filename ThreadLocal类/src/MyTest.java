class MyTest extends Thread {
	private Account account;

	public MyTest(Account account, String name) {
		super(name);
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			if (i == 6) {
				account.setName(getName());
			}
			// 输出同一个账户的账户名和循环变量
			System.out.println(account.getName() + " 账户i值= " + i);
		}
	}

}