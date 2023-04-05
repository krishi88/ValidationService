package com.ebay.item.validation.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class CacheStoreSerializable<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Cache<String, T> cache;
	
    public CacheStoreSerializable(int expiryDuration, TimeUnit timeUnit) {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiryDuration, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }
    public CacheStoreSerializable(Cache<String, T> cache) {
        this.cache = cache;
    }
    public T get(String key) {
    	return cache.getIfPresent(key);
        
    }

    public void add(String key, T value) {
        if(key != null && value != null) {
            cache.put(key, value);
        }
    }
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
    	throw new InvalidObjectException("Proxy required");
    }
    
    private Object writeReplace() {
    	return new CacheStoreProxy<>(this);
    }
    
    private static class CacheStoreProxy<T> implements Serializable {
    	private static final long serialVersionUID = 2642386157015941233L;
		private Cache<String, T> cache;
    	CacheStoreProxy(CacheStoreSerializable<T> cacheStore){
    		this.cache = cacheStore.cache;
    	}
    	
    	private Object readResolve() {
    		return new CacheStoreSerializable(cache);
    	}
    }

}
