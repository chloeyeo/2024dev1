package com.study.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.study.spring.domain.Member;
import com.study.spring.domain.MemberRole;

@SpringBootTest
public class MemberRepositoryTests {
	@Autowired // = DI (dependency injection)
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	
//	@Test // to put test data into db
//	public void testInsertData() {
//		for (int i = 0; i < 10; i++) {
//			Member member = Member.builder()
//					.email("user"+i+"@aaa.com")
//					.pw(passwordEncoder.encode("1111"))
//					.nickname("user"+i)
//					.build();
//			member.addRole(MemberRole.USER); // USER will be stored as 0
//			if (i>=5) member.addRole(MemberRole.MEMBER); // i>=5 will have roles user and member
//			if (i>8) member.addRole(MemberRole.ADMIN); // i>8 will have all roles - user, member and admin
//			memberRepository.save(member);
//		}
//	}
	
	@Test
	public void testRead() {
		String email = "user5@aaa.com";
		Member member = memberRepository.getWithRole(email);
		System.out.println(member.getMemberRoleList());
	}
	
	
}
