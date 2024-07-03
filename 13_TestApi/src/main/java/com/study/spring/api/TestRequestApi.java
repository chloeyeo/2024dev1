package com.study.spring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
public class TestRequestApi {
	//param
	@GetMapping("/test/param") // test/param?name=hong&age=50
	public String requestParam(@RequestParam("name") String name, @RequestParam("age") int age) { 
		
		return "requestParam name: "+name+" age: "+age;
	}
	
	//path variable
	@GetMapping("/test/path/{name}/{age}")
	public String requestPath(@PathVariable("name") String name, @PathVariable("age") int age) {
		return "requestPath name: "+name+" age: "+age;
	}
	
	//request body
//	@PostMapping("/test/body")
//	public String requestBody(@RequestBody TestRequestBody request) {
//		return "requestBody name: "+request.name+" age: "+request.age;
//	}
//	
//	@Data
//	public static class TestRequestBody { // this is a DTO put inside class. A DTO needs a constructor
//		String name;
//		int age;
//	}
	
//	@PostMapping("/test/body")
//	public String requestBody(@RequestBody TestRequestDTO request) {
//		return "requestBody name: "+request.name+" age: "+request.age;
//	}
}
