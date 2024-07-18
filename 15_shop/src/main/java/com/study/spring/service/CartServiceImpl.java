package com.study.spring.service;

import com.study.spring.domain.Cart;
import com.study.spring.domain.CartItem;
import com.study.spring.domain.Member;
import com.study.spring.domain.Product;
import com.study.spring.dto.CartItemDTO;
import com.study.spring.dto.CartItemListDTO;
import com.study.spring.repository.CartItemRepository;
import com.study.spring.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItemListDTO> addOrModify(CartItemDTO cartItemDTO) {
        String email = cartItemDTO.getEmail();
        Long productNo = cartItemDTO.getProductNo();
        int quantity = cartItemDTO.getQuantity();
        Long cartItemNo = cartItemDTO.getCartItemNo();

        if (cartItemNo != null) {
           Optional<CartItem> result = cartItemRepository.findById(cartItemNo);
           CartItem cartItem = result.orElseThrow();
           cartItem.changeQuantity(quantity);
           cartItemRepository.save(cartItem);
           return getCartItems(email);
        }

        Cart cart = getCart(email);

        // if same cartItem IS PRESENT in cart then we ONLY UPDATE the QUANTITY INSTEAD OF CREATING A NEW same cartItem!
        CartItem cartItem = cartItemRepository.getItemOfPno(email,productNo);
        if (cartItem != null) {
            cartItem.changeQuantity(quantity);
        } else {
            Product product = Product.builder().pno(productNo).build();
            cartItem = CartItem.builder().product(product).cart(cart).quantity(quantity).build();
            cartItemRepository.save(cartItem);
        }
        cartItemRepository.save(cartItem);
        return getCartItems(email);
    }

    private Cart getCart(String email) {
        Cart cart = null;
        // getting a Single cart so we use Optional since cart can be null
        Optional<Cart> result = cartRepository.getCartOfMember(email);
        if (result.isEmpty()) {
            Member member = Member.builder().email(email).build();
            Cart tempCart = Cart.builder().owner(member).build();
            cart = cartRepository.save(tempCart);
        } else {
            cart = result.get();
        }
        return cart;
    }

    @Override
    public List<CartItemListDTO> getCartItems(String email) {
        return cartItemRepository.getItemsOfCartDTOByEmail(email);
    }

    @Override
    public List<CartItemListDTO> remove(Long cartItemNo) {
        Long cartNo = cartItemRepository.getCartFromItem(cartItemNo);
        cartItemRepository.deleteById(cartItemNo);
        return cartItemRepository.getItemsOfCartDTOByCart(cartNo);
    }
}
