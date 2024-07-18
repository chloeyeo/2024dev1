package com.study.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.spring.domain.CartItem;
import com.study.spring.dto.CartItemListDTO;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	/*
	 * 1. Must search for all cart data via member's email 
	 */

	// join = inner join. c in "ci.cart=c" is c.cartNo same as writing
	// ci.cart=c.cartNo but cartNo is the 'id' in Cart which is why we can just
	// write c1.cart=c
	// Long cartItemNo, Long productNo, String productName, int quantity, int price, String imgFile
	@Query("select "
			+ " new com.study.spring.dto.CartItemListDTO(ci.cartItemNo, p.pno, p.pname, ci.quantity, p.price, pi.fileName)"
			+ "from "
			+ " CartItem ci join Cart c on ci.cart = c "
			+ " left join Product p on ci.product = p "
			+ " left join p.imageList pi "
			+ "where "
			+ " c.owner.email = :email and pi.ord = 0 order by ci.cartItemNo desc")
	List<CartItemListDTO> getItemsOfCartDTOByEmail(@Param("email") String email);
	
	 @Query("select ci from CartItem ci left join Cart c on ci.cart = c " +
	            "where " +
	            " c.owner.email = :email and ci.product.pno = :pno")
	    CartItem getItemOfPno(@Param("email") String email, @Param("pno") Long pno);

	    @Query("select c.cartNo from Cart c left join CartItem ci on ci.cart = c where ci.cartItemNo = :cartItemNo")
	    Long getCartFromItem(@Param("cartItemNo") Long cartItemNo);

	    @Query("select " +
	            " new com.study.spring.dto.CartItemListDTO(ci.cartItemNo, p.pno, p.pname, ci.quantity, p.price, pi.fileName) " +
	            "from " +
	            " CartItem ci inner join Cart c on ci.cart = c " +
	            " left join Product p on ci.product = p " +
	            " left join p.imageList pi " +
	            "where " +
	            " c.cartNo = :cartNo and pi.ord = 0 " +
	            " order by ci.cartItemNo desc")
	    List<CartItemListDTO> getItemsOfCartDTOByCart(@Param("cartNo") Long cartNo);





}
