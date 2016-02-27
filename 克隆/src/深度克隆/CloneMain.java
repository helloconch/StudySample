package 深度克隆;

public class CloneMain {
	public static void main(String[] args) throws Exception {

		CloneB b1 = new CloneB();
		b1.aInt = 11;
		System.out.println("before clone,b1.aInt = " + b1.aInt);
		System.out.println("before clone,b1.unCA = " + b1.a);

		CloneB b2 = (CloneB) b1.clone();
		b2.aInt = 22;
		b2.a.doubleValue();
		System.out.println("=================================");
		System.out.println("after clone,b1.aInt = " + b1.aInt);
		System.out.println("after clone,b1.unCA = " + b1.a);
		System.out.println("=================================");
		System.out.println("after clone,b2.aInt = " + b2.aInt);
		System.out.println("after clone,b2.unCA = " + b2.a);

		/*
		 * 可以看出，现在b2.unCA的改变对b1.unCA没有产生影响。此时b1.unCA与b2.unCA指向了两个不同的UnCloneA实例，
		 * 而且在CloneB b2 = (CloneB)b1.clone();调用的那一刻b1和b2拥有相同的值，在这里，b1.i = b2.i =
		 * 11。
		 * 
		 * 要知道不是所有的类都能实现深度clone的。例如，如果把上面的CloneB类中的UnCloneA类型变量改成StringBuffer类型，
		 * 看一下JDK API中关于StringBuffer的说明，StringBuffer没有重载clone()方法，
		 * 更为严重的是StringBuffer还是一个final类，这也是说我们也不能用继承的办法间接实现StringBuffer的clone。
		 * 如果一个类中包含有StringBuffer类型对象或和StringBuffer相似类的对象
		 * ，我们有两种选择：要么只能实现影子clone，要么就在类的clone
		 * ()方法中加一句（假设是SringBuffer对象，而且变量名仍是unCA）： o.unCA = new
		 * StringBuffer(unCA.toString()); //原来的是：o.unCA =
		 * (UnCloneA)unCA.clone();
		 * 
		 * 还要知道的是除了基本数据类型能自动实现深度clone以外，String对象是一个例外，它clone后的表现好象也实现了深度clone，
		 * 虽然这只是一个假象，但却大大方便了我们的编程。
		 */

		// 对象序列化

		// private Object cloneObject(Object obj) throws Exception{
		// ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		// ObjectOutputStream out = new ObjectOutputStream(byteOut);
		// out.writeObject(obj);
		//
		// ByteArrayInputStream byteIn = new
		// ByteArrayInputStream(byteOut.toByteArray());
		// ObjectInputStream in =new ObjectInputStream(byteIn);
		//
		// return in.readObject();
		// }
		
//		private static final long serialVersionUID = 48L;
		
		// 虽然Java的序列化非常简单、强大，但是要用好，还有很多地方需要注意。比如曾经序列化了一个对象，可由于某种原因，该类做了一点点改动，然后重新被编译，那么这时反序列化刚才的对象，将会出现异常。
		//
		// 你可以通过添加serialVersionUID属性来解决这个问题。如果你的类是个单态（Singleton）类，是否允许用户通过序列化机制复制该类，如果不允许你需要谨慎对待该类的实现。


	}

}
