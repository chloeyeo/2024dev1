package com.study.springdi.bean;

public class PrinterA implements Printer{
	@Override
	public void print(String message) {
		System.out.println("printer A: " + message);
	}
}
