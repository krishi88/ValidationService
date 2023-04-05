package com.ebay.item.validation;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ebay.item.validation.cache.CacheStore;
import com.ebay.item.validation.cache.CacheStoreImpl;
import com.ebay.item.validation.item.rules.Rule;

@Configuration
public class CacheStoreBean {

	@Bean
    public CacheStore<List<String>> itemSpecsCache() {
        return new CacheStoreImpl<List<String>>();
    }
	
	@Bean
    public CacheStore<List<Rule>> rulesCache() {
        return new CacheStoreImpl<List<Rule>>();
    }

}

