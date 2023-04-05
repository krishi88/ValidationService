package com.ebay.inventory.item.error;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
	
	NullValueError("100","Value must be not null or not empty"),
	TitleLengthError("105","Title length must be less than 50."),
	ItemSpecsLengthError("110","Item specs length must be between 2 and 8."),
	ItemSpecsColorError("115","Color of item required");
	
	final String code;
	final String errorMessage;
	
	public String getCode() {
		return code;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	
	ErrorCode(String code, String errorMessage){
		this.code = code;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString() {
		return "code=" + code + ", error message=" + errorMessage;
	}
}
