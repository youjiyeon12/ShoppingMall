package com.rubypaper.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubypaper.entity.Cart;
import com.rubypaper.entity.CartItem;
import com.rubypaper.entity.Item;
import com.rubypaper.entity.Member;
import com.rubypaper.repository.CartRepository;
import com.rubypaper.repository.ItemRepository;
import com.rubypaper.repository.MemberRepository;
import com.rubypaper.dto.CartDetailDto; 
import java.util.ArrayList; 
import java.util.List; 

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    // 1. 장바구니에 상품 담기 (이게 없으면 컨트롤러에서 에러가 납니다!)
    public Long addCart(String username, Long itemId, int count) {
        
        // 회원 및 장바구니 조회
        Member member = memberRepository.findByUsername(username);
        Cart cart = cartRepository.findByMemberId(member.getId());

        // 장바구니가 없으면 생성
        if(cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        // 상품 조회
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);

        // 장바구니 리스트에서 해당 상품 찾기
        CartItem target = null;
        for(CartItem cartItem : cart.getCartItems()){
            if(cartItem.getItem().getId().equals(item.getId())){
                target = cartItem;
                break;
            }
        }

        if(target != null){
            // 이미 있는 상품이면 수량 증가
            target.addCount(count);
            return target.getId();
        } else {
            // 없는 상품이면 새로 만들어서 리스트에 추가
            CartItem newCartItem = CartItem.createCartItem(cart, item, count);
            cart.getCartItems().add(newCartItem);
            return newCartItem.getId(); 
        }
    }

    // 2. 장바구니 목록 조회
    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String username) {
        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        // 회원 및 장바구니 찾기
        Member member = memberRepository.findByUsername(username);
        Cart cart = cartRepository.findByMemberId(member.getId());

        // 장바구니가 비어있으면 빈 리스트 반환
        if (cart == null) {
            return cartDetailDtoList;
        }

        // 장바구니 상품들을 하나씩 DTO로 변환
        for (CartItem cartItem : cart.getCartItems()) {
            CartDetailDto dto = new CartDetailDto(
                    cartItem.getId(),
                    cartItem.getItem().getItemNm(),
                    cartItem.getItem().getPrice(),
                    cartItem.getCount(),
                    cartItem.getItem().getImgName(),
                    cartItem.getItem().getItemCategory()
            );
            cartDetailDtoList.add(dto);
        }

        return cartDetailDtoList;
    }
    // 장바구니 상품 수량 변경
    public void updateCartItemCount(String username, Long cartItemId, int count) {
        // 1. 회원 및 장바구니 조회
        Member member = memberRepository.findByUsername(username);
        Cart cart = cartRepository.findByMemberId(member.getId());

        if (cart == null) {
            throw new IllegalArgumentException("장바구니가 존재하지 않습니다.");
        }

        // 2. 장바구니 상품 리스트에서 해당 상품 찾기
        CartItem targetItem = null;
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getId().equals(cartItemId)) {
                targetItem = cartItem;
                break;
            }
        }

        // 3. 수량 업데이트
        if (targetItem != null) {
            targetItem.setCount(count);
        } else {
            throw new IllegalArgumentException("장바구니에 없는 상품입니다.");
        }
    }
    
    // 장바구니 상품 삭제
    public void deleteCartItem(String username, Long cartItemId) {
        // 1. 회원 및 장바구니 조회
        Member member = memberRepository.findByUsername(username);
        Cart cart = cartRepository.findByMemberId(member.getId());

        // 2. 장바구니가 있으면, 해당 상품을 리스트에서 찾아서 제거
        if (cart != null) {
            // 조건에 맞는 요소를 리스트에서 삭제함 (DB 반영됨)
            cart.getCartItems().removeIf(item -> item.getId().equals(cartItemId));
        }
    }
}