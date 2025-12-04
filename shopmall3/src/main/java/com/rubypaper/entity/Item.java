package com.rubypaper.entity;

import java.time.LocalDateTime;

import com.rubypaper.constant.ItemCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Item {
    @Id
    @Column(name ="item_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemNm; // 이름
    
    @Column(nullable = false)
    private int price; // 가격
	 
    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory; 

    private String itemType;

    private String color1; // 색깔1
    private String color2; // " 2
    private String color3; // " 3

    private String imgName; // 이미지 파일명
   
}