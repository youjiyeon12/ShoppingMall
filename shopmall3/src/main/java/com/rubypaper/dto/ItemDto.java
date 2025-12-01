package com.rubypaper.dto;

import java.time.LocalDateTime;

public class ItemDto {  //객체화
  private Long id;
  private String itemNm;
  private int price;
  private int stockNumber;
  private String itemSellStatus;
  private String itemDetail;
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
	public int getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}
	public String getItemSellStatus() {
		return itemSellStatus;
	}
	public void setItemSellStatus(String itemSellStatus) {
		this.itemSellStatus = itemSellStatus;
	}
	public String getItemDetail() {
		return itemDetail;
	}
	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
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
}
