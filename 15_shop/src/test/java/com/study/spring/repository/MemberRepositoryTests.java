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
	
	@Test // to put test data into db
	public void testInsertData() {
		Member member = Member.builder()
				.email("user@aaa.com")
				.pw(passwordEncoder.encode("1111"))
				.nickname("user")
				.build();
		member.addRole(MemberRole.USER);
		memberRepository.save(member);
	}
}
