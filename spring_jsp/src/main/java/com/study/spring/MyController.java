package com.study.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller // type @control then press ctrl+space to get full @Controller and import. This registers a BEAN.
public class MyController {
	
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "jsp";
	}
	
	@RequestMapping("/test1")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/test2")
	public String test2(Model model) {
		// Send data using the model object (view -> send data)
		// model is an interface to db
		model.addAttribute("name","홍길동");
		return "test/test2";
	}
	
	// same as test2
	@RequestMapping("/mv")
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView();
		
		List<String> list = new ArrayList<>();
		// list will store methods
		list.add("이순신");
		list.add("홍길동");
		list.add("철수");
		list.add("동수");
		mv.addObject("lists", list); // same as model.addAttribute("name","홍길동"); in test2
		// connect mv to view that will display:
		mv.setViewName("view/myview");
		return mv;
	}
}
