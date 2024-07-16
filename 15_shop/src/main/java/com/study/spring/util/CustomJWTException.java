package com.study.spring.util;

public class CustomJWTException extends RuntimeException {
	// when error occurs it prints out the error message
	public CustomJWTException(String msg) {
		super(msg);
	}
}
