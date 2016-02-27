import java.util.concurrent.locks.ReentrantLock;

public class AccountLock {
	// 可重写锁
	private ReentrantLock lock = new ReentrantLock();
	private String accountNO;
	private double balance;

	public String getAccountNO() {
		return accountNO;
	}

	public void setAccountNO(String accountNO) {
		this.accountNO = accountNO;
	}

	// 提供一个线程安全draw方法来完成取钱操作
	public void draw(double drawAmount) {
		// 对同步锁进行加锁
		lock.lock();
		try {
			if (balance >= drawAmount) {
				System.out.println("取钱:" + drawAmount);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				balance -= drawAmount;
				System.out.println("余额:" + balance);
			} else {
				System.out.println("取钱失败");
			}
		} finally {
			// 释放锁
			lock.unlock();
		}
	}

}
