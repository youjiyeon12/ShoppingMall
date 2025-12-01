package com.rubypaper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rubypaper.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
   //1   JPA -JPQL
   List<Item>  findByItemNm(String itemNm);
   //2
   List<Item>  findByItemNmOrItemDetail(String itemNm,String itemDetail);
  //3
   List<Item>  findByPriceLessThan(int price);
   //4
   List<Item>  findByPriceLessThanOrderByPriceDesc(int price);
  //5
  @Query("select i from Item i where i.itemDetail like %:itemDetail% "
  		+ "order by i.price asc")
  List<Item> findByItemDetail(String itemDetail);
  //6.  native 쿼리방식  mySQL문법에러 발생함
  @Query(value="select * from item i where i.item_detail like "
  + "%:itemDetail% order by i.price desc",nativeQuery =true) 
  List<Item> findByItemDetailNative(@Param("itemDetail")String itemDetail);
  
  }
