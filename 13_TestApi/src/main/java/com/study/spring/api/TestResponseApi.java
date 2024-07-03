package com.study.spring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.spring.api.response.TestResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
public class TestResponseApi {
	@GetMapping("/test/response/string")
	public String response() {
		return "Hello World";
	}
	
//	@GetMapping("/test/response/json")
//	public String jsonResponse() {
//		return "{\"message\": \"Hello World\"}";
//		
//	}
	
	@GetMapping("/test/response/json")
	public TestResponseBody jsonResponse() {
		return new TestResponseBody("chloe011",90);
		
	}
	
//	@Data
//	@NoArgsConstructor
//	@AllArgsConstructor
//	public static class TestResponseBody {
//		String name;
//		int age;
//	}
}
