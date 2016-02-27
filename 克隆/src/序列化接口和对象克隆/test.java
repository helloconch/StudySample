package 序列化接口和对象克隆;

public class test {

	/**
	 * 对于任何一个序列化的对象，都必须要求实现Serializable接口。其次，如果这个类的域中也有引用对象，则也有要求这个引用类型也实现这个接口，。
	 * 最后，序列化方式实现克隆效率不高，没有直接深度克隆的效率高。有兴趣的朋友 可以测试一下。
	 */
	public static void main(String[] args) {
		System.out.println("克隆之前：");
		Address add1 = new Address("中国", 1);
		Employee emp1 = new Employee("rollen", 20, add1);
		System.out.println(emp1);
		System.out.println("克隆之后");
		Employee emp2 = emp1.clone();
		emp2.setName("hello world");
		emp2.setAge(100);
		emp2.getAddress().setNumber(2);
		emp2.getAddress().setState("美国");
		System.out.println(emp1);
		System.out.println("-----");
		System.out.println(emp2);
	}

}
