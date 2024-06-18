package com.study.springdi.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Bean
	public Member member1() {
		Member member1 = new Member();
		member1.setName("Jake");
		member1.setNickname("Jackey");
		member1.setPrinter(new PrinterA());
		return member1;
	}
	
	@Bean(name="some")
	public Member member2() {
		Member member2 = new Member("billy", "bibs", new PrinterA());
		return member2;
	}
	
	@Bean
	public PrinterA printerA() {
		return new PrinterA();
	}
	
	@Bean
	public PrinterB printerB() {
		return new PrinterB();
	}
}
