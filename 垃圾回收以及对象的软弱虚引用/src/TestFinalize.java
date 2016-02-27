public class TestFinalize {
	private static TestFinalize tf = null;

	public void info() {
		System.out.println("测试资源清理的finalize方法");
	}

	public static void main(String[] args) throws Exception {
		// 垃圾回收两种方式
		// System.gc();
		// Runtime.getRuntime().gc();

		// 专门用于清理资源的方法
		// 创建TestFinalize对象立即进入去活状态
		new TestFinalize();
		// 通知系统进行资源回收
		System.gc();
		// 强制垃圾回收机制调用去活对象的finalize方法
		Runtime.getRuntime().runFinalization();
		// System.runFinalization();
		tf.info();

	}

	@Override
	protected void finalize() throws Throwable {
		// 让tf引用试图回收的去活对象
		tf = this;
	}

}
