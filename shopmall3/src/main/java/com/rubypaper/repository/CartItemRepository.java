package com.rubypaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubypaper.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
