package com.ebay.item.validation.service;

import java.util.List;

import com.ebay.item.validation.item.Item;

public interface ItemSpecsNormalizer {
	
	public List<String> normalizeItemSpecs(Item item);
}
