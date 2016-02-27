import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.WeakHashMap;

public class Test {

	public static void main(String[] args) {
		// Java的集合大致分为:Set List和Map三种体系
		// Set代表无序、不可重复的集合
		// List代表有序 ，重复的集合
		// Map代表具有映射关系的集合
		// QUeue jdk1.5之后,代表一种队列集合实现
		// Java的集合类主要由两个接口派生而出:Collection和Map
		// Collection接口是List Set Queue接口父接口,该接口定义方法既可以
		// 用于操作Set集合，也可以用于操作List和Queue集合

		Collection c = new ArrayList();
		Collection books = new HashSet();
		books.add("AAA");
		books.add("BBB");
		// Iterator并不是把集合元素本身传给迭代变量
		// 而是把集合元素的值传给了迭代变量
		Iterator it = books.iterator();
		while (it.hasNext()) {
			String book = (String) it.next();
			if (book.equals("AAA")) {
				it.remove();
				// books.remove(book);

			}
			book = "CCC";
		}
		System.out.println(books);
		Collection cc = new HashSet();
		cc.add(new String("水果"));
		cc.add(new String("苹果"));
		for (Object obj : cc) {
			String each = (String) obj;
			if (each.equals("水果")) {
				// cc.remove(each);//
				// 报错java.util.ConcurrentModificationException
			}
		}
		System.out.println(cc);

		System.out.println(new String("A").equals(new String("A")));
		System.out.println(new String("A") == (new String("A")));
		// ************************HashSet*****************************************
		// 不能保证元素的排列顺序，顺序有可能发生变化
		// HashSet不是同步的，必须通过代码来保证其同步
		// 集合元素值可以是null
		// HashSet判断两个元素相等的标准是两个对象通过equals方法和hashCode方法都相等
		HashSet hs = new HashSet();
		hs.add(new Test().new A());
		hs.add(new Test().new A());
		System.out.println(hs);
		System.out.println(new Test().new A().equals(new Test().new A()));
		System.out.println(new String("A").equals(new String("A")));
		// 表面上HashSet集合里的元素都没有索引，实际上当程序向HashSet集合中添加元素时，
		// HashSet会根据该元素的hashCode值来决定它的存储位置---也就是说
		// 每个元素的hashCode就是它的索引

		// **************HashSet另一个子类LinkedHashSet*****************************
		// LinkedHashSet集合也是根据hashCode值来决定元素存储位置
		// 但它同时使用链表维护元素次序，这样使得元素看起来是以插入顺序保存的
		// 由于LinkedHashSet需要维护元素插入顺序，性能略低于HashSet
		// 但在迭代访问Set里的全部元素时有很好的性能，因为它以链表来维护内部顺序
		LinkedHashSet ll = new LinkedHashSet();
		ll.add("AA");
		ll.add("BB");
		System.out.println(ll);
		// **************TreeSet是SortedSet接口唯一实现******************************
		// TreeSet采用红黑树的数据结构对元素进行排序
		// TreeSet支持2种排序方法，自然排序和定制排序
		// 默认自热排序
		TreeSet nums = new TreeSet();
		nums.add(5);
		nums.add(2);
		nums.add(10);
		nums.add(-9);
		// 已经排好顺序
		System.out.println(nums);
		// 输出集合里第一个元素
		System.out.println(nums.first());
		// 输出集合里最后一个元素
		System.out.println(nums.last());
		// 返回小于4的子集，不包括4
		System.out.println(nums.headSet(4));
		// 返回大于5的子集，如果Set中包含5，子集中还包含5
		System.out.println(nums.tailSet(5));
		// 返回大于等于-3，小于4的子集
		System.out.println(nums.subSet(-3, 4));
		// 小于元素2的最大元素
		System.out.println(nums.lower(3));
		// 大于元素4的最小元素
		System.out.println(nums.higher(4));
		// 如果试图将一个对象添加进TreeSet时，则该对象必须实现Comparable接口

		// 定制排序TreeSet,实现倒序
		TreeSet ts = new TreeSet(new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				M m1 = (M) o1;
				M m2 = (M) o2;
				if (m1.age > m2.age)
					return -1;
				else if (m1.age == m2.age)
					return 0;
				else
					return 1;
			}
		});
		ts.add(new M(5));
		ts.add(new M(-3));
		ts.add(new M(9));
		System.out.println(ts);
		// *************EnumSet**************************************************
		// EnumSet是一个专为枚举类设计集合类，其中集合元素也是有序的
		// 其内部以位向量的形式存储，这种存储形式非常紧凑，高效，因此EnumSet对象占用内存很小，
		// 而且运行效率很好，尤其是当进行批量操作(如调用containsAll和retainAll方法)
		// 不允许加入null元素
		// 创建一个EnumSet集合，集合元素就是Season枚举类全部枚举值
		EnumSet es1 = EnumSet.allOf(Season.class);
		System.out.println(es1);
		EnumSet es2 = EnumSet.noneOf(Season.class);
		System.out.println(es2);
		es2.add(Season.SPRING);
		es2.add(Season.SUMMER);
		System.out.println(es2);
		// 以指定枚举值创建EnumSet集合
		EnumSet es3 = EnumSet.of(Season.SUMMER, Season.WINTER);
		System.out.println(es3);
		EnumSet es4 = EnumSet.range(Season.SUMMER, Season.WINTER);
		System.out.println(es4);
		// es5的集合元素+es4集合元素=Season枚举类的全部枚举值
		EnumSet es5 = EnumSet.complementOf(es4);
		System.out.println(es5);
		Collection enumColl = new HashSet();
		// enumColl添加元素必须是枚举值
		enumColl.add(Season.SPRING);
		enumColl.add(Season.WINTER);
		EnumSet es6 = EnumSet.copyOf(enumColl);
		System.out.println(es6);

		// ***********************List***********************************
		String[] bb = { "a", "b", "c", "d" };
		List lc = new ArrayList();
		for (int i = 0; i < bb.length; i++) {
			lc.add(bb[i]);
		}
		ListIterator listIterator = lc.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
			listIterator.add("--分隔符--");
			System.out.println("lc=" + lc);
		}
		System.out.println("下面开始反向迭代");
		while (listIterator.hasPrevious()) {
			System.out.println(listIterator.previous());
		}

		// ***********************ArrayList和Vector实现类***********************************
		// Vector线程安全，但不提倡使用，Stack是Vector的子类
		Stack stack = new Stack();// 后进先出原则
		stack.push("AA");
		stack.push("BB");
		stack.push("CC");
		System.out.println("输出堆栈顶部第一元素" + stack.peek());
		System.out.println(stack);
		System.out.println("pop出第一元素" + stack.pop());
		System.out.println(stack);
		System.out.println(stack.firstElement());
		System.out.println(stack.lastElement());

		// 重点Arrays.asList()该方法可以把一个数组或指定个数的对象转换成
		// 一个List集合，这个集合既不是ArrayList实例也不是Vector实例，而是
		// Arrays内部类ArrayList实例
		// Array.ArrayList是一个固定长度的List集合，程序只能遍历访问该集合里
		// 的元素，不可增加，删除该集合里的元素。

		List fixedList = Arrays.asList("A1", "A2");
		System.out.println(fixedList.getClass());
		for (int i = 0; i < fixedList.size(); i++) {
			System.out.println(fixedList.get(i));
		}

		// ******************Queue接口********************
		// Queue接口中定义几个方法
		// add()将指定元素加入队列的尾部
		// element()获取头部元素但不删除
		// offer()将指定元素加入队列的尾部,当使用容量限制的队列时,此方法通常比add()方法更好
		// peek()获取头部元素但不删除，如果队列为空返回null
		// poll()获取头部元素并且删除，如果队列为空返回null
		// remove()获取队列头部元素，并且删除
		// Queue有两个实现类:LinkedList和PriorityQueue

		// >>>>>LinkedList属于双向队列,内部以链表形式保持元素，随机访问集合元素性能较差，但在插入，删除元素时性能非常出色>>>>>>>>
		// addFirst()将指定元素插入该双向队列的开头
		// addLast()将指定元素插入该双向队列的末尾
		// descendingIterator()返回以该双向队列对应的迭代器，该迭代器以逆向
		// 顺序迭代队列中的元素
		// getFirst()获取但不删除双向队列第一个元素
		// getLast()获取但不删除双向队列最后一个元素
		// offerFirst()以指定元素插入该双向队列的开头
		// offerLast()以指定元素插入该双向队列的末尾
		// peekFirst()获取但不删除双向队列的第一个元素，若队列为空，则返回null
		// peekLast()获取但不删除双向队列的最后一个元素，若队列为空，则返回null
		// pollFirst()获取并删除双向队列的第一个元素，若队列为空，则返回nu
		// pollLast()获取并删除双向队列的最后一个元素，若队列为空，则返回nu
		// pop() pop出该双向队列所表示的栈中第一个元素
		// push()将一个元素push进该双向队列所表示的栈中(即该双向队列的头部)
		// removeFirst()获取并删除该双向队列的第一个元素
		// removeFirstOccurrence(Object o)删除该双向队列的第一次出现元素o
		// removeLast()获取并删除该双向队列的最后一个元素
		// removeLastOccurrence(Object o)删除该双向队列的最后一次出现元素o

		LinkedList linkedList = new LinkedList();
		// 将字符串加入队列尾部
		linkedList.offer("AAA");
		// 将一个字符串元素入栈
		linkedList.push("BBBB");
		// 将一个字符串加入到队列头部
		linkedList.offerFirst("CCC");
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println(linkedList.get(i));
		}
		System.out.println(linkedList.peekFirst());
		System.out.println(linkedList.peekLast());
		System.out.println(linkedList.pop());
		System.out.println(linkedList);
		System.out.println(linkedList.pollLast());
		System.out.println(linkedList);

		// *********PriorityQueue***************
		// PriorityQueue是一个比较标准的队列实现类，而不是绝对标准的队列实现
		// PriorityQueue保存队列元素顺序并不是加入队列的顺序，而是按队列元素大小
		// 进行重新排序。因此当调用peek或poll方法来取出队列中的元素时，并不是
		// 取出最先进入队列的元素，而是取出队列中最小的元素。
		PriorityQueue pq = new PriorityQueue();
		pq.offer(6);
		pq.offer(-3);
		pq.offer(9);
		pq.offer(0);
		System.out.println(pq);
		System.out.println(pq.peek());

		// *******************Map*********************

		// Hashtable和HashMap存在两点典型区别
		// Hashtable是线程安全而HashMap不是
		// 所以HashMap性能高一点
		// Hashtable不允许使用null作为key和value
		// HashMap可以使用null作为key或value,key只能有一个null
		// LinkedHashMap是HashMap的子类，使用双向链表维护key-value对的次序
		// 该链表定义了迭代顺序，与插入顺序保存一致。

		LinkedHashMap scores = new LinkedHashMap();
		scores.put("语文", 80);
		scores.put("数学", 80);
		scores.put("英语", 80);
		for (Object key : scores.keySet()) {
			System.out.println(key + "---------");
			System.out.println(scores.get(key));
		}
		Set entrySet = scores.entrySet();
		Iterator iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			System.out.println(e.getKey());
			System.out.println(e.getValue());
		}
		Set keySet = scores.keySet();
		Iterator iterator2 = keySet.iterator();
		while (iterator2.hasNext()) {
			String key = iterator2.next().toString();
			System.out.println(key);
			System.out.println(scores.get(key));
		}

		// Properties类是Hashtable的子类

		try {
			Properties props = new Properties();
			props.setProperty("username", "che");
			props.setProperty("password", "123456");
			props.setProperty("nick", URLEncoder.encode("车", "utf-8"));
			props.store(new FileOutputStream("a.ini"), "login");

			System.out.println(URLDecoder.decode(props.getProperty("nick"), "utf-8"));
			Properties props2 = new Properties();
			props2.setProperty("gender", "male");
			// 从属性文件中加载属性名=属性值，追加到Properties里
			props2.load(new FileInputStream("a.ini"));
			System.out.println(props2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// **************SortedMap接口和TreeMap实现类**********************************
		// TreeMap中的方法
		// firstEntry()：返回该Map中最小key所对应key-value对，如果Map为空，则返回null
		// firstKey()：返回该Map中最小key，如果Map为空，则返回null
		// lastEntry()：返回该Map中最大key所对应key-value对，如果Map为空，则返回null
		// lastKey()：返回该Map中最大key，如果Map为空，则返回null
		// higherEntry(Object
		// key)：返回该Map中位于key后一位的key-value对(即大于指定key的最小key所对应的key-value对)，，如果Map为空，则返回null
		// higherKey(Objectkey)：返回该Map中位于key后一位的key(即大于指定key的最小key)，如果Map为空或不存在这样key-value，则返回null
		// lowerEntry(Object
		// key)：返回该Map中位于key前一位的key-value对(即小于指定key的最大key所对应的key-value对)，，如果Map为空，则返回null
		// lowerKey(Objectkey)：返回该Map中位于key前一位的key(即小于指定key的最大key)，如果Map为空或不存在这样key-value，则返回null
		// NavigableMap subMap(Object fromKey,boolean fromInclusive,Object
		// toKey,boolean toInclusive)
		// 返回该Map的子Map,其key的范围从fromKey(是否包括取决于第二个参数)到toKey(是否包括取决于第四个参数)
		// SortedMap subMap(Object fromKey,Object toKey):返回该Map的子Map，其
		// key从fromKey(包括)到toKey(不包括)
		// SortedMap tailMap(Object fromKey)：返回该Map的子Map，其key的范围大于
		// fromKey(包括)的所有key
		// NavigableMap tailMap(bject fromKey,boolean
		// fromInclusive):返回该Map的子Map，其key的范围大于fromKey(是否包括取决于第二个参数)的所有key
		// SortedMap headMap(Object fromKey)：返回该Map的子Map，其key的范围小于
		// fromKey(不包括)的所有key
		// NavigableMap headMap(bject fromKey,boolean
		// fromInclusive):返回该Map的子Map，其key的范围小于fromKey(是否包括取决于第二个参数)的所有key

		// *******************以自然排序TreeMap************
		TreeMap tm = new TreeMap();
		tm.put(new RR(3), "A");
		tm.put(new RR(-5), "B");
		tm.put(new RR(9), "C");
		System.out.println(tm);
		System.out.println(tm.firstKey());
		System.out.println(tm.firstEntry());
		System.out.println(tm.lastKey());
		System.out.println(tm.higherKey(new RR(2)));
		System.out.println(tm.lowerKey(new RR(2)));

		// ***************WeakHashMap实现类********************

		WeakHashMap whm = new WeakHashMap();
		whm.put(new String("A"), new String("A"));
		whm.put(new String("B"), new String("B"));
		whm.put(new String("C"), new String("C"));
		whm.put(new String("D"), new String("D"));
		whm.put("che", new String("che"));
		System.out.println(whm);
		// 通知系统立即进行垃圾回收
		System.gc();
		System.runFinalization();
		System.out.println(whm);

		// ************IdentityHashMap实现类***************************
		// 在处理两个key相等比较独特，在IdentityHashMap中
		// 当且两个key严格相等(key1=key2)时，IdentityHashMap才认为两个key相等
		// 而对于普通HashMap而言，只要key1和key2通过equals比较返回true，且它们hashCode值相等即可。

		// *******************EnumMap实现类****************************
		EnumMap enumMap = new EnumMap(Season.class);
		enumMap.put(Season.SPRING, "AAAAA");
		enumMap.put(Season.SUMMER, "BBBB");
		System.out.println(enumMap);

		// *********************操作集合工具类:Collections******************************
		// 排序操作
		// reverse(List list):反转指定List集合元素顺序
		// shuffle(List list):对List集合元素随机排序(shuffle方法模拟洗牌动作)
		// sort():根据元素自然顺序对指定List集合元素按升序进行排序
		// sort(List list,Comparator c):根据指定Comparator产生顺序对List集合元素进行排序
		// swap(List list,int i,int j):将指定List集合中i处元素和j处元素进行交换
		// rotate(List list,int distance):将指定集合中i处元素和list.length-1-i处元素进行交换

		ArrayList arrayList = new ArrayList();
		arrayList.add(2);
		arrayList.add(-5);
		arrayList.add(3);
		arrayList.add(0);
		System.out.println(arrayList);
		Collections.reverse(arrayList);
		System.out.println(arrayList);
		Collections.sort(arrayList);
		System.out.println(arrayList);
		Collections.shuffle(arrayList);
		System.out.println(arrayList);

		// **********替换和查找操作***********
		// binarySearch(List list ,Object
		// key):使用二分搜索指定List集合，以获得指定对象在List集合中的索引。如果要该
		// 方法可以正常工作，必须保证List中的元素已经处于有序状态。
		// max(Collection coll):根据元素自然顺序，返回给定集合中最大元素
		// max(Collection coll,Comparator
		// comp):根据指定Comparator产生的元素顺序，返回给定集合中最大元素。
		// min(Collection coll):根据元素自然顺序，返回给定集合中最小元素
		// min(Collection coll,Comparator
		// comp):根据指定Comparator产生的元素顺序，返回给定集合中最小元素。
		// fill(List list,Object obj):使用指定元素obj替换指定List集合中的所有元素
		// frequency(Collection c,Object o):返回指定集合中等于指定对象的元素数量
		// indexOfSubList(List source,List
		// target):返回子List对象在母List对象中最后一次出现的位置索引;如果母List中没有出现这样的子List，则返回-1
		// replaceAll(List list,Object oldVal,Object
		// newVal):使用一个新值newVal替换List对象所有的旧值oldVal

		ArrayList aaa = new ArrayList();
		aaa.add(2);
		aaa.add(-5);
		aaa.add(3);
		aaa.add(0);
		System.out.println(aaa);
		System.out.println(Collections.max(aaa));
		System.out.println(Collections.min(aaa));
		Collections.replaceAll(aaa, 0, 1);
		System.out.println(aaa);
		System.out.println(Collections.frequency(aaa, -5));
		Collections.sort(aaa);
		System.out.println(aaa);
		// 只有排序后的List集合才可用二分法查询，输出3
		System.out.println(Collections.binarySearch(aaa, 3));

		// ******************同步控制**************************
		// Collections.synchronizedXXXXX
		Collection a1 = Collections.synchronizedCollection(new ArrayList());
		List a2 = Collections.synchronizedList(new ArrayList());
		Set a3 = Collections.synchronizedSet(new HashSet());
		Map m = Collections.synchronizedMap(new HashMap());

	}

	class A {
		public A() {

		}

	}

}

class M {
	int age;

	public M(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "M对象(age:" + age + ")";
	}
}

enum Season {
	SPRING, SUMMER, FALL, WINTER;
}

class RR implements Comparable {
	int count;

	public RR(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "R(count属性:)" + count + ")";
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("----");
		if (this == obj)
			return true;
		if (obj != null && obj.getClass() == RR.class) {
			RR rr = (RR) obj;
			if (rr.count == this.count) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Object obj) {
		System.out.println("----");
		RR rr = (RR) obj;
		if (this.count > rr.count)
			return 1;
		else if (this.count == rr.count)
			return 0;
		else
			return -1;
	}

}
