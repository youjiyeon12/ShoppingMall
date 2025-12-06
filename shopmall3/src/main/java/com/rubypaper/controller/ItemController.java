package com.rubypaper.controller;

import com.rubypaper.entity.Item;
import com.rubypaper.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/item/{id}")
    public String itemDetail(@PathVariable Long id, Model model) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        model.addAttribute("item", item);

        return "itemDetail";  
    }
}
