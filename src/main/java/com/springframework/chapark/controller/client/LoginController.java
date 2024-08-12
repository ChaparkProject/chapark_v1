package com.springframework.chapark.controller.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.CertificationService;
import com.springframework.chapark.security.SessionManagement;

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
		return "client/mber/login"; // 로그인 페이지 뷰를 반환
	}

	/**
	 * 로그인 체크
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
			/*HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo); // 사용자 정보를 세션에 저장*/
			
			SessionManagement.setSessionInfo(request, "userInfo", userInfo); // 사용자 정보를 세션에 저장
			
			String mberName = (String) userInfo.get("MBER_NAME");
			/*session.setAttribute("mberName", mberName);*/
			SessionManagement.setSessionInfo(request, "mberName", mberName); // 사용자 이름을 세션에 저장
			
			
			model.addAttribute("userInfo", userInfo);
			return "redirect:/"; // 로그인 성공 시 메인 화면으로 리다이렉트
		} else {
			model.addAttribute("loginError", "아이디와 비밀번호가 일치하지 않습니다.");
			return "client/mber/login"; // 로그인 실패 시 로그인으로 이동
		}
	}

	/**
	 * 로그아웃
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false); // 현재 세션을 가져옴 (없으면 null 반환)
		if(session != null) {
			session.invalidate(); // 세션 제거
		}
		return "redirect:/"; // 로그아웃 후 로그인 페이지로 리다이렉트
	}
	
	// 아이디 찾기 페이지
	@RequestMapping(value = "/searchIdPage.do")
	public String searchIdPage(HttpServletRequest request, HttpServletResponse response) {
		return "client/mber/idSearch";
	}
	
	// 아이디 찾기
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchId.do")
	public String searchId(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap, Model model) {
		try {
			Map<String, Object> userIdInfo = chaparkService.selectMap("lo_login.selectIdSearch", commonMap.getMap());
			
			if(userIdInfo != null) {
				String mberName = userIdInfo.get("MBER_NAME").toString();
				String mberTel = userIdInfo.get("MBER_TEL").toString();
				
				if(commonMap.get(mberName).equals(mberName) && commonMap.get(mberTel).equals(mberTel)) {
					model.addAttribute("check", "true");
					model.addAttribute("mberId", userIdInfo.get("mberId"));
				} else {
					model.addAttribute("check", "false");
				}
			}else {
				model.addAttribute("check", "false");
			}
		} catch (Exception e) {
		}
		return "client/mber/idSearch";
	}
	// 비밀번호 찾기
	@RequestMapping(value = "/searchPw.do")
	public String searchPw(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap) {
		try {
			
			Map<String, Object> userPwInfo = chaparkService.selectMap("lo_login.selectPwSearch", commonMap.getMap());
		} catch (Exception e) {
		}
		return "client/mber/pwSearch";
	}
}
