package com.ebay.item.validation.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebay.item.validation.cache.CacheStore;
import com.ebay.item.validation.item.Item;

import lombok.SneakyThrows;

@Service
public class ItemSpecsNormalizerImpl implements ItemSpecsNormalizer {
	private static final Function<String,String> CAPITALIZE_FIRST = (spec) -> {
		String first = spec.substring(0,1);
		String last = spec.substring(1);
		return first.toUpperCase().concat(last.toLowerCase());
	};

	@Autowired
	CacheStore<List<String>> itemSpecsCache;

	
	@Override
	@SneakyThrows
	public List<String> normalizeItemSpecs(Item item){
		List<String> cachedItemSpecs = itemSpecsCache.get(item.getCategoryId());
        if(cachedItemSpecs != null && !cachedItemSpecs.isEmpty()) {
            return cachedItemSpecs;
        }
		List<String> result = item.getItemSpecifics();
		if(item.getItemSpecifics() != null && !(item.getItemSpecifics().isEmpty())) {
			result = item.getItemSpecifics().stream().map(CAPITALIZE_FIRST).collect(Collectors.toList());
		}
		item.setItemSpecifics(result);
		itemSpecsCache.add(item.getCategoryId(), result);
		Thread.sleep(1000);
		return result;
	}
	
}
