package 桥接模式;

public class PorkyNoodle extends AbstractNoodle {

	public PorkyNoodle(Peppery style) {
		super(style);
	}

	@Override
	public void eat() {
System.out.println("PorkyNoodle"+super.style.style());
	}

}
