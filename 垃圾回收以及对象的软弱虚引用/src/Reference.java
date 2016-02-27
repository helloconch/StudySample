public class Reference {

	/**
	 * 强引用（StrongReference）: 对象和数组都是采用了这种强引用的方式，当一个对象被一个或一个以上的引用变量
	 * 所引用时，它处于激活状态，不可能被系统垃圾回收机制回收
	 * 
	 * 软引用(SoftReference) 当一个对象只用软引用时，它有可能被垃圾回收机制回收，比如内存不足时
	 * 
	 * 弱引用(WeakReference) 弱引用级别更低，对于只有弱引用的对象而言，当系统垃圾回收机制运行时
	 * 不管系统内存是否足够，总会回收该对象所占用的内存。
	 * 
	 * 虚引用(PhantomReference) 虚引用完全类似于没有引用，虚引用对对象本身没有太大影响，
	 * 对象甚至感觉不到虚引用的存在。如果一个对象只有一个虚引用时 那它和没有引用的效果大致相同，虚引用主要用于跟踪对象被垃圾回收的状态
	 * ，虚引用不能单独使用，必须合引用队列(ReferenceQueue)联合使用
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
