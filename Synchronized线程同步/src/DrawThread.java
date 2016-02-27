public class DrawThread extends Thread {
	private Account account;
	private double drawAmout;

	public DrawThread(Account account, double drawAmout) {
		super();
		this.account = account;
		this.drawAmout = drawAmout;
	}

	@Override
	public void run() {
		account.draw(drawAmout);
	}

}
