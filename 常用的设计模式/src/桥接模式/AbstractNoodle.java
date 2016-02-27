package 桥接模式;

public abstract class AbstractNoodle {
	protected Peppery style;

	public AbstractNoodle(Peppery style) {
		this.style = style;
	}

	public abstract void eat();
}
