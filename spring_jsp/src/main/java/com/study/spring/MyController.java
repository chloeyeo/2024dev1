package com.study.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
