package com.rubypaper.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rubypaper.constant.ItemCategory;
import com.rubypaper.entity.Item;
import com.rubypaper.entity.Member;
import com.rubypaper.repository.ItemRepository;
import com.rubypaper.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ItemRepository itemRepository;
    
 // 메인화면
    @GetMapping("/")
    public String main(
            @RequestParam(value = "category", defaultValue = "WOMEN") String categoryStr,
            @RequestParam(value = "type", required = false) String itemType,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "price", required = false) String priceRange,
            @RequestParam(value = "searchQuery", required = false) String searchQuery,
            Model model) {
        
        // 1. 카테고리 처리
        ItemCategory category;
        try {
            category = ItemCategory.valueOf(categoryStr.toUpperCase());
        } catch (Exception e) {
            category = ItemCategory.WOMEN;
        }

        // 2. 필터 값 정리 (빈 문자열이면 null로 변환)
        if ("양말 유형".equals(itemType) || "all".equals(itemType)) itemType = null;
        if ("컬러".equals(color) || "all".equals(color)) color = null;
        if (searchQuery != null && searchQuery.trim().isEmpty()) searchQuery = null; // 검색어 공백 처리
        
        // 3. 가격 범위 파싱
        Integer minPrice = null;
        Integer maxPrice = null;
        if (priceRange != null && !priceRange.equals("가격") && !priceRange.equals("all")) {
            String[] prices = priceRange.split("_");
            if (prices.length >= 1) minPrice = Integer.parseInt(prices[0]);
            if (prices.length >= 2 && !prices[1].equals("max")) maxPrice = Integer.parseInt(prices[1]);
        }

        // 4. DB 검색 (searchQuery 포함)
        List<Item> items = itemRepository.findByFilters(category, itemType, color, minPrice, maxPrice, searchQuery);
        
        // 5. 데이터 전달
        model.addAttribute("items", items);
        model.addAttribute("category", category.toString());
        model.addAttribute("selectedType", itemType);
        model.addAttribute("selectedColor", color);
        model.addAttribute("selectedPrice", priceRange);
        model.addAttribute("searchQuery", searchQuery);
        return "main";
    }

    // 회원가입 화면
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("member", new Member());
        return "signup";    
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(
            @Valid Member member,
            BindingResult bindingResult,
            Model model) {

        // 엔티티 유효성 검사 실패
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        // 비밀번호 정규식 검사(해싱 전에)
        String pw = member.getPassword();
        String pwRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$";

        if (!pw.matches(pwRegex)) {
            bindingResult.rejectValue("password", "invalid", "비밀번호는 영문, 숫자, 특수문자를 각각 최소 1개 이상 포함해야 합니다.");
            return "signup";
        }
        
        // 비밀번호 & 비밀번호 확인 체크
        if (!member.getPassword().equals(member.getPasswordConfirm())) {
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "signup";
        }

        // 아이디 중복 검사
        if (memberService.isUsernameDuplicate(member.getUsername())) {
            bindingResult.rejectValue("username", "duplicate", "이미 사용 중인 아이디입니다.");
            return "signup";
        }

        // 이메일 중복 검사
        if (memberService.isEmailDuplicate(member.getEmail())) {
            bindingResult.rejectValue("email", "duplicate", "이미 사용 중인 이메일입니다.");
            return "signup";
        }

        // 회원 저장
        memberService.register(member);

        return "redirect:/login";
    }

    // 로그인 화면
    @GetMapping("/login")
    public String loginForm() {
        return "login";   
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(
            String username,
            String password,
            HttpSession session,
            Model model) {

        Member member = memberService.login(username, password);

        if (member == null) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login";
        }

        // 세션 저장
        session.setAttribute("loginMember", member);

        // 메인 페이지로 이동
        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
