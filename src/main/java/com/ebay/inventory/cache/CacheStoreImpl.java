package com.ebay.inventory.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Service
public class CacheStoreImpl<T> implements CacheStore<T>{
	
	private final Cache<String, T> cache = CacheBuilder.newBuilder()
												    .expireAfterWrite(120, TimeUnit.SECONDS)
												    .concurrencyLevel(Runtime.getRuntime().availableProcessors())
												    .build();
	
    public CacheStoreImpl() {
    }
   
    @Override
    public T get(String key) {
    	return cache.getIfPresent(key);
        
    }

    @Override
    public void add(String key, T value) {
        if(key != null && value != null) {
            cache.put(key, value);
        }
    }

}
