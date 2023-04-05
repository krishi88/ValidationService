package com.ebay.item.validation.service;


import java.util.List;

import com.ebay.item.validation.item.Item;
import com.ebay.item.validation.item.error.ErrorCode;

public interface ValidationService {
	 
	public List<ErrorCode> validateItem(Item item); 

}
