package com.study.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.spring.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	// JPQL (=specifically desinged for JPA, not exactly the same syntax of SQL)
	//p.imageList is also a table referenced in p as foreign key
	@Query("select p, pi from Product p left join p.imageList pi where p1.order=0 and p.deleteFlag=false")
	Page<Object[]> selectList(Pageable pageable);
	
	@Modifying
	@Query("update Product p set p.deleteFlag = :flag where p.no= :pno") // boolean flag goes into :flag and :pno goes into p.no
	void updateToDelete(@Param("pno") Long pno, @Param("flag") boolean flag);
}
