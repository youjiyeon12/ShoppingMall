package com.rubypaper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rubypaper.constant.ItemCategory;
import com.rubypaper.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 상품명으로 검색 (기본 기능)
    List<Item> findByItemNm(String itemNm);
    
    // 가격으로 검색
    List<Item> findByPriceLessThan(int price);
    
    // 카테고리로 상품 찾기
    List<Item> findByItemCategory(ItemCategory itemCategory);
    
    // 필터링 검색 쿼리
    // 값이 NULL이면 조건을 무시하고 전체를 검색하도록 작성됨
    @Query("SELECT i FROM Item i WHERE i.itemCategory = :category " +
           "AND (:itemType IS NULL OR i.itemType = :itemType) " +
           "AND (:color IS NULL OR (i.color1 = :color OR i.color2 = :color OR i.color3 = :color)) " +
           "AND (:minPrice IS NULL OR i.price >= :minPrice) " +
           "AND (:maxPrice IS NULL OR i.price < :maxPrice)")
    List<Item> findByFilters(@Param("category") ItemCategory category,
                             @Param("itemType") String itemType,
                             @Param("color") String color,
                             @Param("minPrice") Integer minPrice,
                             @Param("maxPrice") Integer maxPrice);
    
}