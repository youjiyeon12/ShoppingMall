package com.rubypaper.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubypaper.entity.Cart;
import com.rubypaper.entity.CartItem;
import com.rubypaper.entity.Order;
import com.rubypaper.entity.OrderItem;
import com.rubypaper.repository.CartItemRepository;
import com.rubypaper.repository.CartRepository;
import com.rubypaper.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void orderAll(Long memberId) {

    	Cart cart = cartRepository.findByMemberId(memberId);

    	if (cart == null) {
    	    throw new IllegalArgumentException("장바구니 없음");
    	}

        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems.isEmpty()) {
            throw new IllegalStateException("장바구니가 비어 있습니다.");
        }

        // 주문 생성
        Order order = new Order();
        order.setMemberId(memberId);
        order.setOrderDate(LocalDateTime.now());

        int totalPrice = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (CartItem cartItem : cartItems) {

            OrderItem orderItem = new OrderItem();
            orderItem.setItemId(cartItem.getItem().getId());
            orderItem.setItemName(cartItem.getItem().getItemNm());
            orderItem.setPrice(cartItem.getItem().getPrice());
            orderItem.setCount(cartItem.getCount());
            orderItem.setOrder(order);

            orderItemList.add(orderItem);

            totalPrice += cartItem.getItem().getPrice() * cartItem.getCount();
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItemList);

        orderRepository.save(order);

        // 장바구니 비우기 
        cart.getCartItems().clear();
    }
}
