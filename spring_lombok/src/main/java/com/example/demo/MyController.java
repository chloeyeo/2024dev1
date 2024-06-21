package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // @Controller is bean registration
public class MyController {
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "lombok";
	}
	
	@RequestMapping("/test")
	public String test(Member member, Model model) {
		return "test";
	}
}
