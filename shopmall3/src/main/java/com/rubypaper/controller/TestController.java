package com.rubypaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("/test")
	public String test() {		
		return "3하년 화이팅  업데이트";
	}
}
