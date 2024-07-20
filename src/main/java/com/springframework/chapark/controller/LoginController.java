package com.springframework.chapark.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.CertificationService;

@Controller
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);

	private final CertificationService certificationService;

	@Autowired
	public LoginController(CertificationService certificationService) {
		this.certificationService = certificationService;
	}

	@RequestMapping(value = "/login.do")
	public String loginPage(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap, Model model)
			throws Exception {
		String mberId = (String) commonMap.get("mberId");
		String mberPw = (String) commonMap.get("mberPw");

		boolean success = certificationService.login(mberId, mberPw);
		if (success) {
			HttpSession session = request.getSession();
			session.setAttribute("memberInfo", mberId); // 사용자 정보를 세션에 저장
			return "redirect:/"; // 로그인 성공 시 메인 페이지로 리다이렉트
		} else {
			return "login"; // 로그인 페이지로 이동
		}
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate(); // 세션 무효화
		return "redirect:/login"; // 로그아웃 후 로그인 페이지로 리다이렉트
	}
}
