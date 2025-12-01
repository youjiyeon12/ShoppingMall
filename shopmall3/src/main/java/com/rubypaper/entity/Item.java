package com.rubypaper.entity;

import java.time.LocalDateTime;

import com.rubypaper.constant.ItemSellState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Item {
	 @Id
	 @Column(name ="item_id")  //디비안의 테이블의 생길 id대신 item_id가 생김
	 @GeneratedValue(strategy=GenerationType.IDENTITY)  //H2 MySQL
	 private Long id;	 
	 
	 
	 @Column(nullable =false, length =50)
	 private String itemNm;	 
	 @Column(nullable = false)
	 private int price;	 
	 //추가항목
	 @Column(nullable=false, name ="number")//재고량
	 private int stockNumber;
	 
	 public int getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}
	@Lob  //BobCob 이진 문자열 대량일경우 처리시 사용할 수 있게 하는 어노테이션 
	 private String itemDetail; 	 
	 private ItemSellState itemsell;
	 private LocalDateTime regTime;
	 private LocalDateTime updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getItemDetail() {
		return itemDetail;
	}
	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}
	public ItemSellState getItemsell() {
		return itemsell;
	}
	public void setItemsell(ItemSellState itemsell) {
		this.itemsell = itemsell;
	}
	public LocalDateTime getRegTime() {
		return regTime;
	}
	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", itemNm=" + itemNm + 
				", price=" + price + ", stockNumber=" + stockNumber
				+ ", itemDetail=" + itemDetail + ", itemsell=" + itemsell + ", regTime=" + regTime + ", updateTime="
				+ updateTime + "]";
	}

}
