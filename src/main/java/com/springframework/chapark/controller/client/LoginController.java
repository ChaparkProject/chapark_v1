package com.springframework.chapark.controller.client;

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

import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.CertificationService;

@Controller
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ChaparkService chaparkService;

	private final CertificationService certificationService;

	@Autowired
	public LoginController(CertificationService certificationService) {
		this.certificationService = certificationService;
	}

	/**
	 * 로그인
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginPage() {
		return "client/login"; // 로그인 페이지 뷰를 반환
	}

	/**
	 * 로그인 시도
	 * 
	 * @param request
	 * @param response
	 * @param commonMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap, Model model) throws Exception {
		String mberId = (String) commonMap.get("mberId");
		String mberPw = (String) commonMap.get("mberPw");

		boolean success = certificationService.login(mberId, mberPw, commonMap); // 사용자 정보 true 또는 false
		if (success) {
			Map<String, Object> userInfo = chaparkService.selectMap("lo_login.selectCertificationUserInfo", commonMap.getMap());
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo); // 사용자 정보를 세션에 저장
			
			String mberName = (String) userInfo.get("MBER_NAME");
			session.setAttribute("mberName", mberName);
			
			return "redirect:/"; // 로그인 성공 시 메인 화면으로 리다이렉트
		} else {
			model.addAttribute("loginError", "아이디와 비밀번호가 일치하지 않습니다.");
			return "client/login"; // 로그인 실패 시 로그인으로 이동
		}
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false); // 현재 세션을 가져옴 (없으면 null 반환)
		if(session != null) {
			session.invalidate(); // 세션 제거
		}
		return "redirect:/"; // 로그아웃 후 로그인 페이지로 리다이렉트
	}
}
