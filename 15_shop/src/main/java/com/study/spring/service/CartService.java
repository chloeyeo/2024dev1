package com.study.spring.service;

import com.study.spring.dto.CartItemDTO;
import com.study.spring.dto.CartItemListDTO;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional // bc it's db-related we put transactional so we can revert changes
public interface CartService {
    // add/change cart item
    public List<CartItemListDTO> addOrModify(CartItemDTO cartItemDTO);

    // all cart items list
    public List<CartItemListDTO> getCartItems(String email);

    // delete cart item
    public List<CartItemListDTO> remove(Long cartItemNo);
}
