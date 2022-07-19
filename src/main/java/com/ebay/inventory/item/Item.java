package com.ebay.inventory.item;

import java.util.Arrays;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

	private String siteId;
	private String categoryId;
	private String title;
	private String condition;
	private String price;
	private String quantity;
	private List<String> imageUrls;
	private List<String>  itemSpecifics;
	private String description;
	

	public static void main(String[] args) {
		
		Item item = Item.builder().siteId("1").categoryId("101")
				.title("Title 1").condition("good")
				.price("20.25").quantity("5")
				.imageUrls(Arrays.asList("url1","url2"))
				.itemSpecifics(Arrays.asList("Spec 1","Spec 2"))
				.description("Description 1").build();
		
	}
}

