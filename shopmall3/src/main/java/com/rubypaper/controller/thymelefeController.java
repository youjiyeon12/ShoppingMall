package com.rubypaper.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rubypaper.dto.ItemDto;

@Controller
//@RequestMapping(value ="/thymeleaf")
public class thymelefeController {
	@GetMapping("/ex0")      //http://localhost:8090/ex0
	public String  ex0() {
	   return "/ex0";	
	}
	@GetMapping("/ex1")
	public String  ex1(Model model) {
		model.addAttribute("data","수업중~~");
	   return "/ex1";	
	}
	@GetMapping("/ex2")
	public String  ex2(Model model) {
		ItemDto itemDto = new ItemDto();
		//자동으로 id 생성되므로
		itemDto.setItemDetail("제품상세설명Describe");
		itemDto.setItemNm("크리스마스굿즈");
	    itemDto.setPrice(13000);
	    itemDto.setRegTime(LocalDateTime.now());   
	    
		model.addAttribute("itemDto",itemDto);
	   return "/ex2";	
	}
	@GetMapping("/ex3")
	public String ex3(Model model) {
		List<ItemDto> list =new ArrayList<>();
			for(int i =0; i<=10; i++) {
				ItemDto itemDto = new ItemDto();
				itemDto.setItemDetail("상품 상세 설명"+ i);
				itemDto.setItemNm("테스트 상품"+i);
				itemDto.setPrice(12000+i);
				itemDto.setRegTime(LocalDateTime.now());
				list.add(itemDto);
			}
		model.addAttribute("list", list);			
		return "/ex3";
	}
	@GetMapping("/ex4")
	public String ex4(Model model) {
		List<ItemDto> list =new ArrayList<>();
			for(int i =0; i<=10; i++) {
				ItemDto itemDto = new ItemDto();
				itemDto.setItemDetail("상품 상세 설명"+ i);
				itemDto.setItemNm("테스트 상품"+i);
				itemDto.setPrice(1000*i);
				itemDto.setRegTime(LocalDateTime.now());
				list.add(itemDto);
			}
		model.addAttribute("list", list);			
		return "/ex4";
	}
	@GetMapping("/ex5")
	public String ex5(Model model) {				
		return "/ex5";
	}		
	@GetMapping("/ex6")
	public String ex6(@RequestParam String param1, 
	                  @RequestParam String param2, Model model) {
	    model.addAttribute("param1", param1);
	    model.addAttribute("param2", param2);
	    System.out.println("------------------ex6 parameter--"+
	      param1+",  "+param2+"------------");        
	    return "/ex6";
	}

	@GetMapping({"/ex7","/ex8"}) 
	public void ex7() {
		
	 }
	


}
