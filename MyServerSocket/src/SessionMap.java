import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SessionMap<K, V> extends HashMap<K, V> {
	// 根据value删除指定项
	public void removeByValue(Object value) {
		for (Object key : keySet()) {
			if (get(key) == value) {
				remove(key);
				break;
			}
		}
	}

	// 获取所有value组成的Set集合
	public Set<V> valueSet() {
		Set<V> result = new HashSet<V>();
		// 遍历所有key组成的集合
		for (K key : keySet()) {
			// 将每个Key对应的value添加到result集合中
			result.add(get(key));
		}
		return result;
	}

	// 根据value查找key
	public K getKeyByValue(V val) {
		for (K key : keySet()) {
			if (get(key).equals(val) && get(key) == val) {
				return key;
			}
		}
		return null;
	}

	// 重写HashMap的put方法，该方法不允许value重复
	@Override
	public V put(K key, V value) {
		for (V val : values()) {
			if (val.equals(value) && val.hashCode() == value.hashCode()) {
				throw new RuntimeException("该实例不允许有重复value!");
			}
		}
		return super.put(key, value);
	}

}
