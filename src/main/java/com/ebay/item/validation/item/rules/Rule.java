package com.ebay.item.validation.item.rules;

import java.io.Serializable;
import java.util.List;

import com.ebay.item.validation.item.Item;
import com.ebay.item.validation.item.error.ErrorCode;

public interface Rule extends Serializable {
	
	public List<ErrorCode> eval(Item item);
	
}
