package 代理模式;

public class ImageProxy implements Image {
	// 组合一个Image实例，作为代理对象
	private Image image;

	public ImageProxy(Image image) {
		this.image = image;
	}

	/**
	 * 该方法用于控制对被代理对象的访问 并根据需要负责创建和删除被代理对象
	 */
	@Override
	public void show() {
		// 只有当真正需要调用image的show方法时才创建被代理对象
		if (image == null) {
			image = new BigImage();
		}
		image.show();
	}

}
