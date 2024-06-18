package com.study.springdi.bean;

import org.springframework.stereotype.Component;

@Component("printerA") // this creates bean
public class PrinterA implements Printer{
	@Override
	public void print(String message) {
		System.out.println("printer A: " + message);
	}
}
