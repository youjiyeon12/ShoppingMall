package com.rubypaper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.rubypaper.dto.CartDetailDto;
import java.util.List;

import com.rubypaper.entity.Member; 
import com.rubypaper.service.CartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    @ResponseBody
    public ResponseEntity order(@RequestParam("itemId") Long itemId,
                                @RequestParam("count") int count,
                                @SessionAttribute(name = "loginMember", required = false) Member loginMember) {

        // 로그인하지 않았으면(세션에 정보가 없으면) 에러 메시지 반환
        if (loginMember == null) {
            return new ResponseEntity<String>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }

        try {
            // 세션에 있는 회원 정보(loginMember)의 아이디(Username)를 사용
            cartService.addCart(loginMember.getUsername(), itemId, count);
            return new ResponseEntity<String>("장바구니에 담았습니다.", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/cart")
    public String cartHist(@SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model) {
        
        // 1. 로그인 안 했으면 로그인 페이지로
        if (loginMember == null) {
            return "redirect:/login";
        }

        // 2. 서비스에서 장바구니 목록 가져오기
        List<CartDetailDto> cartDetailList = cartService.getCartList(loginMember.getUsername());
        
        // 3. 화면으로 전달
        model.addAttribute("cartItems", cartDetailList);
        
        return "cartList";
    }
    // 장바구니 수량 변경 요청 처리
    @PostMapping("/cart/update")
    @ResponseBody
    public ResponseEntity updateCartItem(@RequestParam("cartItemId") Long cartItemId,
                                         @RequestParam("count") int count,
                                         @SessionAttribute(name = "loginMember", required = false) Member loginMember) {

        if (count <= 0) {
            return new ResponseEntity<String>("최소 1개 이상 담아주세요.", HttpStatus.BAD_REQUEST);
        } else if (loginMember == null) {
            return new ResponseEntity<String>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }

        try {
            // 서비스 호출
            cartService.updateCartItemCount(loginMember.getUsername(), cartItemId, count);
            return new ResponseEntity<String>("수량이 변경되었습니다.", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // 장바구니 상품 삭제 요청 처리
    @DeleteMapping("/cart/item/{cartItemId}")
    @ResponseBody
    public ResponseEntity deleteCartItem(@PathVariable("cartItemId") Long cartItemId,
                                         @SessionAttribute(name = "loginMember", required = false) Member loginMember) {

        if (loginMember == null) {
            return new ResponseEntity<String>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }

        try {
            // 서비스 호출 (삭제)
            cartService.deleteCartItem(loginMember.getUsername(), cartItemId);
            return new ResponseEntity<String>("상품이 삭제되었습니다.", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}