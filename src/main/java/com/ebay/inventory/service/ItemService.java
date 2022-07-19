package com.ebay.inventory.service;


import java.util.List;

import com.ebay.inventory.item.Item;
import com.ebay.inventory.item.error.ErrorCode;

public interface ItemService {
	 
	public List<ErrorCode> validateItem(Item item); 

}
