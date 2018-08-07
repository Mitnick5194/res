package com.ajie.utils.cache;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 以ConcurrentHashMap为存储介质的缓存，线程安全
 * 
 * @author niezhenjie
 *
 * @param <K>
 * @param <V>
 */
public class MapCache<K, V> implements Cache<K, V> {

	/** 缓存最多项 */
	public static final int MAXIMUN_CAPACITY = (1 << 27) - 1;

	protected Map<K, V> map;
	/** 最后修改时间 */
	protected Date lastModify;

	/**
	 * 无参构造方法，初始化一个默认大小的、线程安全的Map
	 */
	public MapCache() {
		map = new ConcurrentHashMap<K, V>();
	}

	public MapCache(int size) {
		map = new ConcurrentHashMap<K, V>(size);
	}

	public MapCache(Map<? extends K, ? extends V> map) {
		map = new ConcurrentHashMap<K, V>(map);
	}

	public MapCache(Cache<? extends K, ? extends V> cache) {
		MapCache<? extends K, ? extends V> c;
		if (cache instanceof MapCache) {
			c = (MapCache<? extends K, ? extends V>) cache;
			map = new ConcurrentHashMap<K, V>(c.map);
		}
	}

	public V put(K key, V value) {
		checksize();
		V v = map.put(key, value);
		if (null == v || v != value) {
			updateModifyDate();
		}
		return v;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		int size = map.size();
		int size2 = m.size();
		if (size2 > MAXIMUN_CAPACITY)
			throw new IndexOutOfBoundsException();
		if (MAXIMUN_CAPACITY - size < size2)
			throw new IndexOutOfBoundsException();
		updateModifyDate();
		map.putAll(m);
	}

	public V remove(Object key) {
		return map.remove(key);
	}

	public V get(Object key) {
		return map.get(key);
	}

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Date lastModifyDate() {
		return lastModify;
	}

	private void updateModifyDate() {
		lastModify = new Date();
	}

	public void clear() {
		map.clear();
	}

	private void checksize() {
		checksize(map.size());
	}

	private void checksize(int size) {
		if (size >= MAXIMUN_CAPACITY)
			throw new IndexOutOfBoundsException();
	}
	

}
