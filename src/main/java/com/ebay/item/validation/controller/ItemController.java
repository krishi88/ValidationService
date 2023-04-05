package com.ebay.item.validation.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.item.validation.item.Item;
import com.ebay.item.validation.item.error.ErrorCode;
import com.ebay.item.validation.service.RulesService;
import com.ebay.item.validation.service.ValidationService;


@RestController
public class ItemController {
	
	@Autowired
	ValidationService itemService;
	
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

