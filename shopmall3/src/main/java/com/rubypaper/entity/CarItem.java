package com.rubypaper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cart_item")
public class CarItem extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "cart_item_id")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	private Cart car;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	private int count;
	public static CarItem createcartItem(Cart cart, Item item, int count) {
		return null;
		
	}
}
