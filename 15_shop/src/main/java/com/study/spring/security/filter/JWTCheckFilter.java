package com.study.spring.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.study.spring.dto.MemberDTO;
import com.study.spring.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter { // checks whether user has the (not expired) token or not (and can thus visit this page)
	// if user has no token or has expired token then it redirects user to the login page
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getRequestURI();
		log.info("check uri ---- " + path);
		if (path.startsWith("/api/member")) return true; // then don't filter - because if we don't even have created a token yet how can we check if there's an unexpired token or not?? so first when we're logging in as a member (which creates the token) we say to NOT filter in this case i.e. don't check(filter) for this token at first when user logs in
		return false; // do filter by default
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// this is at the first stage of login token processing
		// so should check e.g. the time of token creation etc
		log.info("---------------doFilterInternal-----------------");
        log.info("---------------doFilterInternal-----------------");
        log.info("---------------doFilterInternal-----------------");


        String authHeaderStr = request.getHeader("Authorization");

        try {
            // Bearer access token...
            String accessToken = authHeaderStr.substring(7); // In header: Authorization key has value: Bearer <access_token>. The substring(7) of 'Bearer <access_token>' will be just the access_token part!
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);

            log.info("JWT claims: " + claims);
            //filterChain.doFilter(request, response); //이하 추가
            String email = (String) claims.get("email");
            String pw = (String) claims.get("pw");
            String nickname = (String) claims.get("nickname");
            Boolean social = (Boolean) claims.get("social");
            List<String> roleNames = (List<String>) claims.get("roleNames");

            MemberDTO memberDTO = new MemberDTO(email, pw, nickname, social.booleanValue(), roleNames);
            log.info("-----------------------------------");
            log.info(memberDTO);
            log.info(memberDTO.getAuthorities());
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(memberDTO, pw, memberDTO.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response); // always go to doFilter at the end of filter chain
        }catch(Exception e){
            log.error("JWT Check Error..............");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }
	}
	
}
