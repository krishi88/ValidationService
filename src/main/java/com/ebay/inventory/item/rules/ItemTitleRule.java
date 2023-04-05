package com.ebay.inventory.item.rules;

import static  com.ebay.inventory.utils.StringUtil.isNullOrEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.ebay.inventory.enums.ItemCondition;
import com.ebay.inventory.item.Item;
import com.ebay.inventory.item.error.ErrorCode;

public class ItemTitleRule implements Rule, Serializable {

	private static final int MAX_TITLE_LENGTH = 50;

	private static final long serialVersionUID = 3412129119244202368L;
	
	private static final ItemTitleRule INSTANCE = new ItemTitleRule();
	
	private static final Predicate<Item> ITEM_PREDICATE = (item)-> !(item.getCondition().equalsIgnoreCase(ItemCondition.USED.name()));
	
	private ItemTitleRule() {
	}
	
	public static ItemTitleRule getInsance() {
		return INSTANCE;
	}
	
	public List<ErrorCode> eval(Item item){
		List<ErrorCode> result = new ArrayList<>();
		if(ITEM_PREDICATE.test(item)) {
			if(isNullOrEmpty(item.getTitle())) {
				result.add(ErrorCode.NullValueError);
			}
			if(!isNullOrEmpty(item.getTitle()) && item.getTitle().length() > MAX_TITLE_LENGTH) {
				result.add(ErrorCode.TitleLengthError);
			}
		}
		return result;
	}
	
	private Object readResolve() {
		return INSTANCE;
	}
}
