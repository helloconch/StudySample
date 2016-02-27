package 观察者模式;
public class Test {
	public static void main(String[] args) {
		String sql = "china is %s";
		String format = String.format(sql, "abc");
		System.out.println(format);
		Product p = new Product("���ӻ�", 1000);
		// ����}��۲��߶���
		NameObserver no = new NameObserver();
		PriceObserver po = new PriceObserver();
		p.registerObserver(no);
		p.registerObserver(po);
		p.setName("������");
		p.setPrice(100);

	}
}
