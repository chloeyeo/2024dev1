package com.study.spring.jpa;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
	@Autowired
	MemberService memberService;
	
	@GetMapping("/")
	public String root() {
		return "menu"; // goes to menu.jsp when Uri / is visited
	}
	
	@PostMapping("/insert") // insert?username=han
	public String insert(@RequestParam("username") String username, @RequestParam("content") String content, Model model) {
		
//		Member user = new Member();
//		user.changeUsername(username);
//		user.changeCreateDate(LocalDate.now());
		
		Member user = Member.builder()
				.username(username)
				.content(content)
				.createDate(LocalDate.now())
				.build(); // SAME as 3 commented lines above
		
		//Member user = Member.builder().username(username).build(); // this ALSO works with builder()
		// even if there's no single parameter constructor in Member class - builder() automatically creates this constructor
		
		Member result = memberService.insert(user);
		
		model.addAttribute("member", result); // access member.username, member.createDate etc in insert.jsp file
		
		return "insert"; // returns insert.jsp
	}
}
