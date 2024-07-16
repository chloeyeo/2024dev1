package com.study.spring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.study.spring.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	// instead of writing joins ourselves by query, we use entity graph to do the same thing.
	@EntityGraph(attributePaths = {"memberRoleList"}) // join email with memberRoleList. member left join email.
	@Query("select m from Member m where m.email = :email") // email로 들어와서 memberRoleList (in the above line)과 join을 한다
	Member getWithRole(@Param("email") String email);
}
