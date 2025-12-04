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
            Model model) {
        
        // 1. 카테고리 변환 (String -> Enum)
        ItemCategory category;
        try {
            category = ItemCategory.valueOf(categoryStr.toUpperCase());
        } catch (Exception e) {
            category = ItemCategory.WOMEN;
        }

        // 2. 필터 값 정리 ('전체' 선택 시 null로 처리)
        if ("양말 유형".equals(itemType) || "all".equals(itemType)) itemType = null;
        if ("컬러".equals(color) || "all".equals(color)) color = null;
        
        // 3. 가격 범위 파싱
        Integer minPrice = null;
        Integer maxPrice = null;
        
        if (priceRange != null && !priceRange.equals("가격") && !priceRange.equals("all")) {
            String[] prices = priceRange.split("_");
            if (prices.length >= 1) {
                minPrice = Integer.parseInt(prices[0]); // 최소
            }
            if (prices.length >= 2 && !prices[1].equals("max")) {
                maxPrice = Integer.parseInt(prices[1]); // 최대
            }
        }

        // 4. DB 검색 실행
        List<Item> items = itemRepository.findByFilters(category, itemType, color, minPrice, maxPrice);
        
        // 5. 화면에 데이터 전달
        model.addAttribute("items", items);
        model.addAttribute("category", category.toString());
        model.addAttribute("selectedType", itemType);
        model.addAttribute("selectedColor", color);
        model.addAttribute("selectedPrice", priceRange);
        
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
