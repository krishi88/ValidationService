package com.ebay.inventory.service;


import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.ebay.inventory.cache.CacheStore;
import com.ebay.inventory.item.Item;
import com.ebay.inventory.item.rules.ItemSpecsRule;
import com.ebay.inventory.item.rules.ItemTitleRule;
import com.ebay.inventory.item.rules.Rule;



@RunWith(SpringRunner.class)
public class ItemSpecsNormalizerImplTest {
	
	private static final List<Rule> RULES = List.of(ItemTitleRule.getInsance(),ItemSpecsRule.getInsance());
	private static final List<String> ITEM_SPECS = List.of("Abc", "Def");
	
	static ItemSpecsNormalizerImpl service = null;
	
	static CacheStore<List<String>> itemSpecsCacheMock;
	

	
	@BeforeClass
    public static void setupMocks() {
		service = new ItemSpecsNormalizerImpl();
		itemSpecsCacheMock = createMock(CacheStore.class);
		ReflectionTestUtils.setField(service,"itemSpecsCache",itemSpecsCacheMock);
	}
	
    @Before
    public void prepareMocks() {
       
    }
    
    @After
    public void resetMocks() {
        EasyMock.reset(itemSpecsCacheMock);
    }

	@Test
	public void testNormalizeItemSpecsWhenNoneInCache(){
		expect(itemSpecsCacheMock.get(anyString())).andReturn(List.of()).once();
		itemSpecsCacheMock.add(anyString(),anyObject(List.class));
    	replay(itemSpecsCacheMock);
    	
		List<String> specs = service.normalizeItemSpecs(createTestItem());
		Assert.assertTrue(specs.stream().allMatch(spec ->{
			Character first = spec.charAt(0);
			return Character.isUpperCase(first);
		}));
	}
	@Test
	public void testNormalizeItemSpecsWhenSomeInCache(){
		expect(itemSpecsCacheMock.get(anyString())).andReturn(ITEM_SPECS).once();
    	replay(itemSpecsCacheMock);
    	
		List<String> specs = service.normalizeItemSpecs(createTestItem());
		Assert.assertTrue(specs.stream().allMatch(spec ->{
			Character first = spec.charAt(0);
			return Character.isUpperCase(first);
		}));
	}
	private Item createTestItem() {
		Item item = Item.builder().siteId("1").categoryId("10")
				.title("Title 1Title 1Title Title 1Title 11Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1").condition("good")
				.price("20.25").quantity("5")
				.imageUrls(List.of("url1","url2"))
				.itemSpecifics(List.of("spec 1","spec 2"))
				.description("Description 1").build();
		return item;
	}

}

