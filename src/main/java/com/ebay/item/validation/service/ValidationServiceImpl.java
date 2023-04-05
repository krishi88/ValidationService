package com.ebay.item.validation.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebay.item.validation.cache.CacheStore;
import com.ebay.item.validation.item.Item;
import com.ebay.item.validation.item.error.ErrorCode;
import com.ebay.item.validation.item.rules.Rule;

@Component
public class ValidationServiceImpl implements ValidationService {
	
	private static final String ALL_RULES = "ALL_RULES";

	@Autowired
	RulesService rulesSevice;
	
	@Autowired
	CacheStore<List<Rule>> rulesCache;
	
	@Autowired
	ItemSpecsNormalizer itemSpecsCapitalizer;
	
	public List<ErrorCode> validateItem(Item item){
		List<Rule> rules = getRulesFromCacheIfFound();
		item.setItemSpecifics(itemSpecsCapitalizer.normalizeItemSpecs(item));
		return rules.stream().flatMap(rule -> rule.eval(item).stream()).collect(Collectors.toList());
	}

	private List<Rule> getRulesFromCacheIfFound() {
		List<Rule> rules = rulesCache.get(ALL_RULES);
		if(rules == null || rules.isEmpty()) {
			rules = rulesSevice.getAllRules();
			rulesCache.add(ALL_RULES, rules);
		}
		return rules;
	}
}

