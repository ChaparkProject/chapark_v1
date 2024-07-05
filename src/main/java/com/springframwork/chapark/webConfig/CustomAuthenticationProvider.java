package com.springframwork.chapark.webConfig;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		
		// AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
		String mberId = token.getName();
		String mberPw = (String) token.getCredentials();
		
		User uDTO = new UserDTO();
		// ... DB에서 아이디로 사용자 조회
		
		// 비밀번호 일치 여부 체크
		if (!passwordEncoder.matches(mberPw, uDTO.getUsrPw())) {
			throw new BadCredentialsException(uDTO.getUsrId() + " Invalid password");
		}
		
		// principal(접근대상 정보), credential(비밀번호), authorities(권한 목록)를 token에 담아 반환
		return new UsernamePasswordAuthenticationToken(uDTO, mberPw, uDTO.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
