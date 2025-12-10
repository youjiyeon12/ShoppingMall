package com.rubypaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rubypaper.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // 현재 로그인한 회원의 장바구니 찾기
    Cart findByMemberId(Long memberId);
}