//存钱后，不能进行第二次存，等待取走，再通知，存钱
public class DrawAndDeposit {
	private String accountNo;
	private double balance;
	private boolean flag = false;// 账号是否已有存款

	public DrawAndDeposit(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public DrawAndDeposit() {
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public synchronized void draw(double drawAmount) {
		try {
			// 如果flag为假，表明账号还没有人存钱进去,则取钱方法阻塞
			if (!flag) {
				wait();
			} else {
				// 执行取钱
				balance -= drawAmount;
				System.out.println("账号余额:" + balance);
				flag = false;
				// 唤醒其他线程
				notifyAll();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void deposit(double depositAmount) {
		try {
			if (flag) {
				wait();
			} else {
				// 存钱
				balance += depositAmount;
				flag = true;
				// 唤醒其它线程
				notifyAll();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
