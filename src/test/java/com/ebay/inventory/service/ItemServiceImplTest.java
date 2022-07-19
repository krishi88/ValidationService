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
import com.ebay.inventory.item.error.ErrorCode;
import com.ebay.inventory.item.rules.ItemSpecsRule;
import com.ebay.inventory.item.rules.ItemTitleRule;
import com.ebay.inventory.item.rules.Rule;



@RunWith(SpringRunner.class)
public class ItemServiceImplTest {
	
	private static final List<Rule> RULES = List.of(ItemTitleRule.getInsance(),ItemSpecsRule.getInsance());
	private static final List<String> ITEM_SPECS = List.of("abc");
	
	static ItemServiceImpl service = null;
	
	static RulesService rulesSeviceMock;
	static CacheStore<List<Rule>> rulesCacheMock;
	static ItemSpecsNormalizer itemSpecsCapitalizerMock;
	

	
	@BeforeClass
    public static void setupMocks() {
		service = new ItemServiceImpl();
		rulesSeviceMock = createMock(RulesService.class);
		ReflectionTestUtils.setField(service,"rulesSevice",rulesSeviceMock);
		rulesCacheMock = createMock(CacheStore.class);
		ReflectionTestUtils.setField(service,"rulesCache",rulesCacheMock);
		itemSpecsCapitalizerMock = createMock(ItemSpecsNormalizer.class);
		ReflectionTestUtils.setField(service,"itemSpecsCapitalizer",itemSpecsCapitalizerMock);
	}
	
    @Before
    public void prepareMocks() {
       
    }
    
    @After
    public void resetMocks() {
        EasyMock.reset(rulesSeviceMock);
        EasyMock.reset(rulesCacheMock);
        EasyMock.reset(itemSpecsCapitalizerMock);
    }

	@Test
	public void testValidateItem(){
		expect(rulesCacheMock.get(anyString())).andReturn(RULES).once();
    	replay(rulesCacheMock);
    	expect(itemSpecsCapitalizerMock.normalizeItemSpecs(anyObject(Item.class))).andReturn(ITEM_SPECS).once();
    	replay(itemSpecsCapitalizerMock);
    	
		List<ErrorCode> errors = service.validateItem(createTestItem());
		System.out.println("###...errors="+errors);
		Assert.assertEquals(3,errors.size());
	}
	
	private Item createTestItem() {
		Item item = Item.builder().siteId("1").categoryId("10")
				.title("Title 1Title 1Title Title 1Title 11Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1Title 1").condition("good")
				.price("20.25").quantity("5")
				.imageUrls(List.of("url1","url2"))
				.itemSpecifics(List.of("Spec 1"))
				.description("Description 1").build();
		return item;
	}

}

