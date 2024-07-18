package com.study.spring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.spring.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	// instead of writing joins ourselves by query, we use entity graph to do the same thing.
	@EntityGraph(attributePaths = {"memberRoleList"}) // join email with memberRoleList. member left join email.
	@Query("select m from Member m where m.email = :email") // :email here gets joined with memberRoleList (in the above line)
	Member getWithRole(@Param("email") String email);
}
