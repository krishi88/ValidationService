package com.ebay.inventory.item.rules;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import com.ebay.inventory.item.Item;
import com.ebay.inventory.item.error.ErrorCode;
import com.ebay.inventory.service.ItemSpecsNormalizer;

public class ItemSpecsRule implements Rule, Serializable {
	
	private static final long serialVersionUID = 5766368686148679966L;
	
	private static final ItemSpecsRule INSTANCE = new ItemSpecsRule();
	
	private static final String MODEL_PATTERN_STRING = ".*[Mm]odel\\s*\\:\\s*.+$";
	private static final Pattern MODEL_PATTERN = Pattern.compile(MODEL_PATTERN_STRING);
	private static final Predicate<String> MODEL_PREDICATE = MODEL_PATTERN.asPredicate();
	private static final Predicate<Item> ITEM_PREDICATE = (item)-> (Long.parseLong(item.getSiteId()) > 0l && Long.parseLong(item.getSiteId()) <= 100l);
	
	ItemSpecsNormalizer itemSpecsCapitalizer;
	
	public static ItemSpecsRule getInsance() {
		return INSTANCE;
	}

	public List<ErrorCode> eval(Item item){
		List<ErrorCode> result = new ArrayList<>();
		if(ITEM_PREDICATE.test(item)) {
			List<String> itemSpecs = item.getItemSpecifics();
			
			if((itemSpecs == null) || (itemSpecs.size() == 0)) {
				result.add(ErrorCode.NullValueError);
			}
			if((itemSpecs != null) && (itemSpecs.size() < 2) || (itemSpecs.size() > 8)) {
				result.add(ErrorCode.ItemSpecsLengthError);
			}
			if((itemSpecs != null) && (itemSpecs.size() > 0)) {
				if(item.getItemSpecifics().stream().noneMatch(MODEL_PREDICATE)) {
					result.add(ErrorCode.ItemSpecsModelError);
				}
			}
		}
		return result;
	}
	
	private Object readResolve() {
		return INSTANCE;
	}
	
}
