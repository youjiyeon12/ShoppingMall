package com.rubypaper.test;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.constant.ItemSellState;
import com.rubypaper.entity.Item;
import com.rubypaper.repository.ItemRepository;

@SpringBootTest
public class ItemRepositoryTest {   //Repository Test에서 구현함
    @Autowired
	ItemRepository itemRepository;
    @Test
    @DisplayName("일단한개의 튜플 저장해보기")
    public void createItemTest() {
    	Item item = new Item();
    	item.setItemNm("testing");
    	item.setPrice(10000);
    	item.setItemDetail("test Detail");
    	item.setItemsell(ItemSellState.SELL);
    	item.setRegTime(LocalDateTime.now());
    	item.setUpdateTime(LocalDateTime.now());
    	
       Item savedItem =itemRepository.save(item);
       System.out.println(savedItem.toString());
    }
    @Test
    @DisplayName("여러개 튜플 저장하기")
    public void createItemListTest() {
      for(int i =1; i<=10; i++) {
    	Item item = new Item();
    	item.setItemNm("테스트상품"+i);
    	item.setPrice(10000+ i);
    	item.setItemDetail("테스트상품 상세설명"+i);
    	item.setItemsell(ItemSellState.SELL);
    	item.setRegTime(LocalDateTime.now());
    	item.setUpdateTime(LocalDateTime.now());
    	
       Item savedItem =itemRepository.save(item);
      }      
    }
    @Test
    @DisplayName("findByItemNm()----")
    public void findByItemNmTest() {  //1
    	this.createItemListTest();
    	List<Item> itemList =itemRepository.findByItemNm("테스트상품3");
    	System.out.println("\n\n---findByItemNm()----");
    	for(Item i:itemList)
    		System.out.println(i.toString());
    }
    @Test
    @DisplayName("findByItemNmOrItemDetail()----")
    public void findByItemNmorItemDetailTest() {  //2
    	this.createItemListTest();
    	List<Item> itemList 
    	  =itemRepository.findByItemNmOrItemDetail("테스트상품3","테스트상품 상세설명8");
    	System.out.println("\n----findByItemNmOrItemDetail()----------------------");
    	for(Item i:itemList)
    		System.out.println(i.toString());
    }
    @Test
    @DisplayName("findByPriceLessThan()----")
    public void findByPriceLessThanTest() {  //3
    	List<Item> itemList 
    	  =itemRepository.findByPriceLessThan(10006);
    	System.out.println("\nfindByPriceLessThan()----***************");
    	for(Item i:itemList)
    		System.out.println(i.toString());
    }    
    @Test
    @DisplayName("findByPriceLessThanOrderByPriceDesc()----")
    public void findByPriceLessThanOrderByPriceDescTest() {  //3
    	List<Item> itemList 
    	  =itemRepository.findByPriceLessThanOrderByPriceDesc(10006);
    	System.out.println("\nfindByPriceLessThanOrderByPriceDesc()----$$$$$$$$$$$");
    	for(Item i:itemList)
    		System.out.println(i.toString());
    }  
    //5
    @Test
    @DisplayName("JPQL")
    public void findByItemDetailTest() {
    	createItemListTest();
    	List<Item>  itemList =itemRepository.findByItemDetail("상세설명2");
    System.out.println("\n\n----------------JPQL");
    	for(Item item: itemList)
    	System.out.println(item);
    }
	//6.
    @Test
    @DisplayName("native query")
    public void findByItemDetailNativeTest() {
      createItemTest();
  	  List<Item> itemList =itemRepository.findByItemDetailNative(
  			  "상세설명4");
  	System.out.println("\n\n----------------native query");
	for(Item item: itemList)
	     System.out.println(item);
  	  
    }
}
