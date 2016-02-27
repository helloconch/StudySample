package 桥接模式;

public class BeefNoodle extends AbstractNoodle {

	public BeefNoodle(Peppery style) {
		super(style);
	}

	@Override
	public void eat() {
		System.out.println("BeefNoodle:" + super.style.style());
	}

}
