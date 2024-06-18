package com.study.springdi.bean;

import org.springframework.stereotype.Component;

@Component("printerB") // printerB is bean (instance) name form class PrinterB
public class PrinterB implements Printer{
	@Override
	public void print(String message) {
		System.out.println("printer B: " + message);
	}
}
