package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/sample/{var1}/{var2}")
	public String sample(Model model, @PathVariable("var1") String firstVar, @PathVariable("var2") String secondVar) {
		model.addAttribute("firstVar", firstVar);
		model.addAttribute("secondVar", secondVar);
		return "sample";
	}
}
