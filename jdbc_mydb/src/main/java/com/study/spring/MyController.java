package com.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring.dao.IMyDbDao;
import com.study.spring.dto.MyDbDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {
	@Autowired
	IMyDbDao dao;
	
	@RequestMapping("/")
	public String root() {
		// this redirection is done by the CONTROLLER
		return "redirect:list"; // redirects to list page ("/list") (we should make a list.jsp file and when we visit "/list" it'll show this list.jsp)
	}
	
	@RequestMapping("/list")
	public String listPage(Model model) {
		// inside list.jsp <c:forEach var="dto" items="${lists}"> we put items = 'lists' so here
		// we do mode.addAttribute("lists").
		model.addAttribute("lists", dao.listDao());
		return "list";
	}
	
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		model.addAttribute("dto", dao.viewDao(sId));
		return "view"; // view.jsp
	}
}
