package com.ebay.inventory.cache;

public interface CacheStore<T> {
	public T get(String key);
	public void add(String key, T value);
}
