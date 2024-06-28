package com.springframework.chapark.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springframework.chapark.common.ChaparkService;

@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChaparkService ChaparkService;

	@SuppressWarnings({ "rawtypes", "null" })
	@RequestMapping(value = "/login.do")
	public String loginMain(Model model, Map paramMap, HttpServletRequest request) throws Exception {

		Map memberInfo = ChaparkService.selectMap("lo_login.selectUserInfo", paramMap);

		if (memberInfo != null ||  !memberInfo.isEmpty()) { // 정보가 null이지 않을 때 or ""이지 않을때
			if(memberInfo.get("MBER_ID").equals( paramMap.get("MBER_ID")) && memberInfo.get("MBER_PW").equals( paramMap.get("MBER_PW"))) {
				HttpSession session = request.getSession();
				session.setAttribute("memberInfo", memberInfo);
				session.setMaxInactiveInterval(60 * 30); // 세션 유지시간 30분 설정,  초 * 분 = 초 ex) 60초 * 30분 = 1800초
			}
		} else { //로그인 정보가 없을 시
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("url", "/chapark/login.do");
		}

		return "login";
	}

	@RequestMapping(value = "/logout.do")
	public String logout(Model model, Map paramMap) {

		return "logout";
	}

}
