package com.ebay.inventory.service;

import java.util.List;

import com.ebay.inventory.item.Item;

public interface ItemSpecsNormalizer {
	
	public List<String> normalizeItemSpecs(Item item);
}
