package com.ebay.inventory.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.inventory.item.Item;
import com.ebay.inventory.item.error.ErrorCode;
import com.ebay.inventory.service.ItemService;
import com.ebay.inventory.service.RulesService;


@RestController
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	RulesService rulesService;
	
	@PostMapping("/item/validate")
	public List<ErrorCode> validateItem(@RequestBody Item item) {
		return itemService.validateItem(item);
	}
	
	/**
	 * This is for testing in local only
	 * 
	 * Rule Objects will be stored in database
	 * using this API.
	 * 
	 * This is intended to be in some other application.
	 * 
	 * 
	 * @param ruleId
	 * @return
	 */
	@PostMapping("/rule/saveRule/{ruleId}")
	public Boolean saveRule(@PathVariable String ruleId) {
		return rulesService.saveRule(ruleId);
	}
	
	
}

