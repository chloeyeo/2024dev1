package com.study.spring.service;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.spring.dto.ProductDTO;

@SpringBootTest
public class ProductServiceTests {
	@Autowired
	private ProductService productService;
	
	@Test
	public void testRegister() {
		// we use builder() instead of new ProductDTO(...) (instead of instantiating via 'new')
		for (int i=0;i<10;i++) {
			ProductDTO productDTO = ProductDTO.builder()
					.pname("new product")
					.productDescription("new product description")
					.price(5000).build();
			productDTO.setUploadFileNames(
					List.of(
							UUID.randomUUID()+"_"+"test1.jpg",
							UUID.randomUUID()+"_"+"test2.jpg",
							UUID.randomUUID()+"_"+"test3.jpg"
							)
					);
			productService.register(productDTO);
		}
	}
}
