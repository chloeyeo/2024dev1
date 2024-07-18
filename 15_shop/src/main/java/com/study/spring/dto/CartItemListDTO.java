package com.study.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CartItemListDTO {
	private Long cartItemNo;
	private Long productNo;
	private String productName;
	private int quantity;
	private int price;
	private String fileName;
	
	// constructor for repository projection creation
	public CartItemListDTO(Long cartItemNo, Long productNo, String productName, int quantity, int price,
			String fileName) {
		this.cartItemNo = cartItemNo;
		this.productNo = productNo;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.fileName = fileName;
	}
}
