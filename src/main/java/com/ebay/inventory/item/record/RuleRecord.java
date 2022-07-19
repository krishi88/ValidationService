package com.ebay.inventory.item.record;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ebay.inventory.item.rules.Rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleRecord {

	public RuleRecord(String ruleName, Rule rule) {
		this.ruleName = ruleName;
		this.rule= (Serializable)rule;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ruleId;
	
	private String ruleName;

	private Serializable rule;

}
