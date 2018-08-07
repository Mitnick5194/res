package com.ajie.utils.cache;

public class CacheFactory {
	private static Cache<Object, Object> cache;

	public static Cache<Object, Object> getCache() {
		if (null == cache) {
			synchronized (CacheFactory.class) {
				if (null == cache) {
					cache = new MapCache<Object, Object>();
					return cache;
				}
			}
		}
		return null;
	}

	/*
	 * public Cache<K, V> getCache() { if (null == cache) { synchronized (cache)
	 * { if (null == cache) cache = new MapCache<K, V>(); } } return cache; }
	 */

}
