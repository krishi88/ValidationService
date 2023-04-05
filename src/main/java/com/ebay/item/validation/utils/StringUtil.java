package com.ebay.item.validation.utils;

public class StringUtil {

	private StringUtil() {
	}

	public static boolean isNullOrEmpty(String value) {
    	return value == null || value.trim().isEmpty();
    }
}
