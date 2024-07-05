package com.springframwork.chapark.webConfig;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class UserCustomLoginFailHandler implements AuthenticationFailureHandler {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		// ...
		
		// 로그인 실패 후 페이지 이동 시 해당 코드 적용
		response.sendRedirect("/user/loginView");
	}
}
