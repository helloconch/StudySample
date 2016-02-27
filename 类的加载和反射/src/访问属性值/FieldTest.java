package 访问属性值;

import java.lang.reflect.Field;

public class FieldTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		Person p = new Person();
		// 获取Person对应的Class对象
		Class<Person> personClazz = Person.class;
		//获取Person类名为那么的属性
		//使用getDeclaredField，表明可获取各种访问控制符的field
		Field nameField = personClazz.getDeclaredField("name");
		//设置通过反射访问该Field时取消访问权限检查
		nameField.setAccessible(true);
		//调用set方法为p对象的指定Field设置值
		nameField.set(p, "cheyanxu");
		Field ageField = personClazz.getDeclaredField("age");
		ageField.setAccessible(true);
		ageField.setInt(p, 0);
		System.out.println(p );
	}

}
