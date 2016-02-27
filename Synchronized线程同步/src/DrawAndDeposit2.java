import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//存钱后，不能进行第二次存，等待取走，再通知，存钱
public class DrawAndDeposit2 {
	//显示定义Lock对象
	private final Lock lock=new ReentrantLock();
	//获得指定Lock对象对应的条件变量
	private final Condition cond=lock.newCondition();
	private String accountNo;
	private double balance;
	private boolean flag = false;// 账号是否已有存款

	public DrawAndDeposit2(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public DrawAndDeposit2() {
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

	public  void draw(double drawAmount) {
		try {
			// 如果flag为假，表明账号还没有人存钱进去,则取钱方法阻塞
			if (!flag) {
				cond.await();
			} else {
				// 执行取钱
				balance -= drawAmount;
				System.out.println("账号余额:" + balance);
				flag = false;
				// 唤醒其他线程
				cond.signalAll();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

	public synchronized void deposit(double depositAmount) {
		try {
			if (flag) {
				cond.await();
			} else {
				// 存钱
				balance += depositAmount;
				flag = true;
				// 唤醒其它线程
				cond.signalAll();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}
