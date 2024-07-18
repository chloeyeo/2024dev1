package com.study.spring.dto;

import lombok.Data;

@Data
public class CartItemDTO { //cartNo값이 있는지 체크
	private String email;
	private Long productNo;
	private int quantity;
	private Long cartItemNo; 
}
