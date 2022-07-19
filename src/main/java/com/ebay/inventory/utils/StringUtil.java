package com.ebay.inventory.utils;

public class StringUtil {

	private StringUtil() {
	}

	public static boolean isNullOrEmpty(String value) {
    	return value == null || value.trim().isEmpty();
    }
}
