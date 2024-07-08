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
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.CustomUserDetails;
import com.springframework.chapark.security.CustomUserDetailsService;

@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChaparkService ChaparkService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" } )
	@RequestMapping(value = "/login.do")

	public String loginMain(CommonMap commonMap, HttpServletRequest request) throws Exception {
			
			//Map memberInfo = ChaparkService.selectMap( "lo_login.selectUserInfo", CommonMap.getMap());
		try {
			String username = commonMap.get("username").toString();
			CustomUserDetails userDetails = CustomUserDetailsService.loadUserByUsername(username, commonMap, request);

			if (userDetails != null) { // 정보가 null이 아니고 비어있지 않을 때
					//세션에 저장
					HttpSession session = request.getSession();
					session.setAttribute("userDetails", userDetails);
					session.setMaxInactiveInterval(60 * 30); // 세션 유지시간 30분 설정, 60초 * 30분 = 1800초
					return "redirect:/main.jsp";
			} else { // 로그인 정보가 없을 시
				return "login";
			}
		}catch (Exception e) {
	        // 예외 발생 시
	        return "main";
	    }
	}

	@RequestMapping(value = "/logout.do")
	public String logout(Model model, Map paramMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("memberInfo");
		return "logout";
	}

}
