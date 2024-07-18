package com.study.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.spring.domain.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query("select c from Cart c where c.owner.email = :email") // :email is the @param string email we get it from argument
	Optional<Cart> getCartOfMember(@Param("email") String email);
}
