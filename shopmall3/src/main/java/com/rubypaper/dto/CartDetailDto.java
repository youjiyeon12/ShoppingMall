package com.rubypaper.dto;

import com.rubypaper.constant.ItemCategory;

import lombok.Data;

@Data
public class CartDetailDto {

    private Long cartItemId; // 장바구니 상품 ID
    private String itemNm;   // 상품명
    private int price;       // 가격
    private int count;       // 수량
    private String imgName;  // 이미지 파일명
    private ItemCategory itemCategory; // 카테고리

    // 생성자
    public CartDetailDto(Long cartItemId, String itemNm, int price, int count, String imgName, ItemCategory itemCategory) {
        this.cartItemId = cartItemId;
        this.itemNm = itemNm;
        this.price = price;
        this.count = count;
        this.imgName = imgName;
        this.itemCategory = itemCategory;
    }
}