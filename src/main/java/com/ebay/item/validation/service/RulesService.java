package com.ebay.item.validation.service;


import java.util.List;

import com.ebay.item.validation.item.rules.Rule;

public interface RulesService {
	 
	public Rule getRuleForRuleId(String ruleId);
	
	public List<Rule> getAllRules();
	
	public Boolean saveRule(String ruleId);

}
