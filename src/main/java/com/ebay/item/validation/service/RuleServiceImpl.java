package com.ebay.item.validation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebay.item.validation.item.record.RuleRecord;
import com.ebay.item.validation.item.repository.RuleRepository;
import com.ebay.item.validation.item.rules.ItemSpecsRule;
import com.ebay.item.validation.item.rules.ItemTitleRule;
import com.ebay.item.validation.item.rules.Rule;

@Service
public class RuleServiceImpl implements RulesService {
	
	@Autowired
	private RuleRepository ruleRepo;
	
	public Rule getRuleForRuleId(String ruleId) {
		Optional<RuleRecord> record = ruleRepo.findById(Long.parseLong(ruleId));
		
		return (Rule)record.get().getRule();
	}
	
	public List<Rule> getAllRules() {
		List<RuleRecord> records = ruleRepo.findAll();
		return records.stream().map(ruleRecord -> (Rule)ruleRecord.getRule()).collect(Collectors.toList());
	}
	
	public Boolean saveRule(String ruleId) {
		if(ruleId.equalsIgnoreCase("titleRule")) {
			ruleRepo.save(new RuleRecord("titleRule", ItemTitleRule.getInsance()));
		}else if(ruleId.equalsIgnoreCase("itemSpecs")) {
			ruleRepo.save(new RuleRecord("itemSpecs", ItemSpecsRule.getInsance()));
		}
		return true;
	}
}


