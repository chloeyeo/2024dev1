package com.study.spring.controller;

import com.study.spring.domain.Cart;
import com.study.spring.dto.CartItemDTO;
import com.study.spring.dto.CartItemListDTO;
import com.study.spring.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @PreAuthorize("#cartItemDTO.email == authentication.name")
    @PostMapping("/change")
    public List<CartItemListDTO> changeCart(@RequestBody CartItemDTO cartItemDTO) {
        if (cartItemDTO.getQuantity() <= 0) { // then delete that cartItem
            return cartService.remove(cartItemDTO.getCartItemNo());
        }
        return cartService.addOrModify(cartItemDTO);
    }

    @GetMapping("/items")
    public List<CartItemListDTO> getCartItems(Principal principal) {
        String email = principal.getName();
        log.info("email {}", email);
        return cartService.getCartItems(email);
    }

    @DeleteMapping("/{cartItemNo}")
    public List<CartItemListDTO> deleteCartItem(@PathVariable("cartItemNo") Long cartItemNo) {
        return cartService.remove(cartItemNo);
    }

}
