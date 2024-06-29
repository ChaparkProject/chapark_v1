package com.springframework.chapark.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springframework.chapark.common.ChaparkService;

@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChaparkService ChaparkService;

	@SuppressWarnings({ "rawtypes", "unchecked" } )
	@RequestMapping(value = "/login.do")

	public String loginMain(@RequestParam Map<String, Object> paramMap, HttpServletRequest request) throws Exception {
			paramMap.put("mberId",(String) paramMap.get("mberId"));
			paramMap.put("mberPw",(String) paramMap.get("mberPw"));
			
			Map memberInfo = ChaparkService.selectMap( "lo_login.selectUserInfo", paramMap);

			if (memberInfo != null && !memberInfo.isEmpty()) { // 정보가 null이 아니고 비어있지 않을 때

					HttpSession session = request.getSession();
					session.setAttribute("memberInfo", memberInfo);
					session.setMaxInactiveInterval(60 * 30); // 세션 유지시간 30분 설정, 60초 * 30분 = 1800초
					//return "WEB-INF/views/mberTest";
			} else { // 로그인 정보가 없을 시
				return "login";
			}
		return "login";
	}

	@RequestMapping(value = "/logout.do")
	public String logout(Model model, Map paramMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("memberInfo");
		return "logout";
	}

}
