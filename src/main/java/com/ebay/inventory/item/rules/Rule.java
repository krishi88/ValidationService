package com.ebay.inventory.item.rules;

import java.io.Serializable;
import java.util.List;

import com.ebay.inventory.item.Item;
import com.ebay.inventory.item.error.ErrorCode;

public interface Rule extends Serializable {
	
	public List<ErrorCode> eval(Item item);
	
}
