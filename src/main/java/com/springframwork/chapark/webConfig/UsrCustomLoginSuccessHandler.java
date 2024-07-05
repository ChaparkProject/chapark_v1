package com.springframwork.chapark.webConfig;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class UsrCustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		// Spring Context Holder에 인증 정보 저장
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// ...
		
		// 로그인 후 페이지 이동 시 해당 코드 적용
		// response.sendRedirect("/hello");
	}
}
