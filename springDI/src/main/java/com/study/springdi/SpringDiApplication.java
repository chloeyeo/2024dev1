package com.study.springdi;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.study.springdi.bean.Config;
import com.study.springdi.bean.Member;
import com.study.springdi.bean.Printer;

//@SpringBootApplication
public class SpringDiApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringDiApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		
		// must import Member bc it's from a different package
		Member member1 = (Member) context.getBean("member1"); // downcasting (Member)
		member1.print();
		
		Member member2 = (Member) context.getBean("some");
		member2.print();
		
		Member member3 = context.getBean("some", Member.class);
		member3.print();
		
		Printer printer = context.getBean("printerB", Printer.class);
		member1.setPrinter(printer);
		member1.print();
	}

}
