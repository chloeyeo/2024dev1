package com.study.spring.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.study.spring.domain.Cart;
import com.study.spring.domain.CartItem;
import com.study.spring.domain.Member;
import com.study.spring.domain.Product;
import com.study.spring.dto.CartItemListDTO;

import jakarta.transaction.Transactional;
//import lombok.extern.log4j.Log4j2;

@SpringBootTest
//@Log4j
public class CartRepositoryTests {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Test
	public void testListOfMember() {
		String email = "user1@aaa.com";
		List<CartItemListDTO> cartItemList = cartItemRepository.getItemsOfCartDTOByEmail(email);
		for (CartItemListDTO dto: cartItemList) {
			System.out.println(dto);
		}
	}
	
	@Transactional
	@Commit
	@Test
	public void testInsertByProduct() {
		String email = "user1@aaa.com";
		Long pno = 4L;
		int quantity = 10;
		
		// email이 cart_item에 있는지 확인
		CartItem cartItem = cartItemRepository.getItemOfPno(email, pno);
		
		if (cartItem != null) { // if cartItem for that product already exists then just increase the quantity - no need to add another cartItem for the same product
			cartItem.changeQuantity(quantity);
			cartItemRepository.save(cartItem);
			return;
		}
		
		Cart cart = null;
		// check if cart exists
		Optional<Cart> result = cartRepository.getCartOfMember(email);
		if (result.isEmpty()) {
			Member member = Member.builder().email(email).build();
			Cart tempCart = Cart.builder().owner(member).build();
			
			cart = cartRepository.save(tempCart);
		} else {
			cart = result.get();
		}
		Product product = Product.builder().pno(pno).build();
		cartItem = CartItem.builder().cart(cart).quantity(quantity).product(product).build();
		cartItemRepository.save(cartItem);
	}

	// clean이 들어가있는거는 test까지 다 보고 build를 하는거 - if test fails, 'clean' won't build (for deployment)
	// in gradle clean bootwar/war

	@Test
	public void testUpdateByCartItemNo() {
		Long cartItemId = 5L;
		int quantity = 100;

		Optional<CartItem> result = cartItemRepository.findById(cartItemId);
		CartItem cartItem = result.orElseThrow();

		cartItem.changeQuantity(quantity);

		cartItemRepository.save(cartItem);
	}

	// write tests for backend then you don't have to use postman/talend to test apis

	@Test
	public void testDeleteThenList() {
		Long cartItemNo = 2L;

		Long cartNo = cartItemRepository.getCartFromItem(cartItemNo);
		cartItemRepository.deleteById(cartItemNo);

		List<CartItemListDTO> cartItemList = cartItemRepository.getItemsOfCartDTOByCart(cartNo);

		for (CartItemListDTO dto: cartItemList) {
			System.out.println(dto);
		}
	}

}
