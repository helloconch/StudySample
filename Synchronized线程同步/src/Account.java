public class Account {
	private String accountNO;
	private double balance;

	public Account(String accountNO, double balance) {
		super();
		this.accountNO = accountNO;
		this.balance = balance;
	}

	public String getAccountNO() {
		return accountNO;
	}

	public void setAccountNO(String accountNO) {
		this.accountNO = accountNO;
	}

	public double getBalance() {
		return balance;
	}

	// 提供一个线程安全方法完成取钱操作
	public synchronized void draw(double drawAmount) {
		if (balance >= drawAmount) {
			System.out.println("取钱成功:" + drawAmount);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			balance -= drawAmount;
			System.out.println("余额为:" + balance);
		} else {
			System.out.println("取钱失败");
		}
	}
}
