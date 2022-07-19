package com.ebay.inventory.service;


import java.util.List;

import com.ebay.inventory.item.rules.Rule;

public interface RulesService {
	 
	public Rule getRuleForRuleId(String ruleId);
	
	public List<Rule> getAllRules();
	
	public Boolean saveRule(String ruleId);

}
