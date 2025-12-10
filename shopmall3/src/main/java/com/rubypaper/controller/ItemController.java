package com.rubypaper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rubypaper.entity.Item;
import com.rubypaper.entity.Member;
import com.rubypaper.repository.ItemRepository;
import com.rubypaper.service.ItemService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/item/{id}")
    public String itemDetail(@PathVariable Long id, Model model) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        model.addAttribute("item", item);

        return "itemDetail";  
    }
    
    // 상품 등록 폼 (ADMIN 전용)
    @GetMapping("/item/new")
    public String createItemForm(HttpSession session, Model model) {

        Member member = (Member) session.getAttribute("loginMember");

        if (member == null) {
            return "redirect:/login";
        }

        if (!member.getRole().equals("ADMIN")) {
            model.addAttribute("errorMessage", "관리자만 접근할 수 있는 페이지입니다.");
            return "error/403"; // 하단에 설명
        }

        model.addAttribute("item", new Item());
        return "itemForm";
    }

    // 상품 저장 처리
    @PostMapping("/item/new")
    public String saveItem(
            @Valid Item item,
            BindingResult bindingResult,
            @RequestParam("imageFile") MultipartFile imageFile,
            HttpSession session,
            Model model) {

        Member member = (Member) session.getAttribute("loginMember");

        if (member == null) {
            return "redirect:/login";
        }

        if (!member.getRole().equals("ADMIN")) {
            model.addAttribute("errorMessage", "관리자만 상품을 등록할 수 있습니다.");
            return "error/403";
        }

        if (bindingResult.hasErrors()) {
            return "itemForm";
        }

        try {
            itemService.saveItem(item, imageFile);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "이미지 업로드 중 오류가 발생했습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
    }


}
