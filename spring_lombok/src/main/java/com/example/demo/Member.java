package com.example.demo;

import lombok.Data;

@Data // this adds constructor, getter & setter methods for the fields automatically
public class Member {
	private String id;
	private String name;
}
