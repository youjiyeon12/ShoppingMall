package com.rubypaper.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rubypaper.entity.Member;
import com.rubypaper.entity.Order;
import com.rubypaper.repository.OrderRepository;
import com.rubypaper.service.OrderService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    // 장바구니 전체 주문 처리
    @PostMapping("/order")
    @ResponseBody
    public String orderAll(HttpSession session) {

    	Member member = (Member) session.getAttribute("loginMember");

    	if (member == null) {
    	    return "로그인이 필요합니다.";
    	}

    	Long memberId = member.getId();
    	orderService.orderAll(memberId);
    	return "success";
    }

    // 구매 이력 조회 페이지
    @GetMapping("/orders")
    public String orderList(HttpSession session, Model model) {

    	Member member = (Member) session.getAttribute("loginMember");

    	// 로그인 체크
    	if (member == null) {
    	    return "redirect:/login";
    	}

    	// 세션에서 memberId 가져오기
    	Long memberId = member.getId();

    	// 구매내역 조회
    	List<Order> orders = orderRepository.findByMemberId(memberId);

    	model.addAttribute("orders", orders);

    	return "orderList"; 
    }
}
