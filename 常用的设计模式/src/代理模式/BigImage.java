package 代理模式;

public class BigImage implements Image {
	public BigImage() {
		try {
			// 程序暂停3s模拟系统开销
			Thread.sleep(3000);
			System.out.println("图片装载成功");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void show() {
		System.out.println("绘制实际的大图片");
	}

}
