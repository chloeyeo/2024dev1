package com.study.spring.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.spring.domain.Member;
import com.study.spring.dto.MemberDTO;
import com.study.spring.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
@ReadingConverter
public class CustomUserDetailsService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("----------------------------------#2 loadUserByUsername ------------------------------------------");
		Member member = memberRepository.getWithRole(username);
		if (member==null) {
			System.out.println("member with given username not found");
			throw new UsernameNotFoundException("username not found");
		}
//		if (member == null) throw new UsernameNotFoundException("not found:");
//		@SuppressWarnings("unchecked")
		
		MemberDTO memberDTO = new MemberDTO(member.getEmail()
				, member.getPw(), member.getNickname(), member.isSocial(),
				member.getMemberRoleList().stream().map(memberRole -> memberRole.name()).collect(Collectors.toList()));
		log.info("memberDTO: " + memberDTO);
		return memberDTO;
	}

}
