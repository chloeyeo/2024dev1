package com.study.spring.controller;

import com.study.spring.dto.MemberDTO;
import com.study.spring.service.MemberService;
import com.study.spring.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
public class SocialController {
    private final MemberService memberService;

    @GetMapping("/api/member/kakao")
    public Map<String,Object> getMemberFromKakao(@RequestParam("accessToken") String accessToken){ // this comes in from frontend axios request
        log.info(accessToken);
//        memberService.getKakaoMember(accessToken);
        MemberDTO memberDTO = memberService.getKakaoMember(accessToken);

        Map<String,Object> claims = memberDTO.getClaims();
        String jwtAccessToken = JWTUtil.generateToken(claims, 10); // 10 minutes
        String jwtRefreshToken = JWTUtil.generateToken(claims,60*24); // one day

        claims.put("accessToken", jwtAccessToken);
        claims.put("refreshToken", jwtRefreshToken);

        return claims;
//        return null;
    }
}

