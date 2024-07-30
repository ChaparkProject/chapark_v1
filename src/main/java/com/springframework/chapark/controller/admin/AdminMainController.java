package com.springframework.chapark.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springframework.chapark.common.ChaparkLogger;
import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.utils.ChaparkUtil;

@Controller
public class AdminMainController {
	
	@Autowired
	private  ChaparkUtil chaparkUtil;
	
	@Autowired
	private  ChaparkService chaparkService;
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/admin/main.do")
	public String adminMain(Model model, HttpSession session, CommonMap commonMap, HttpServletResponse response) {
		
		Map userInfo =(Map) chaparkUtil.sessionUserInfo(session);
		Boolean flag = false;

		if(chaparkUtil.authorCheck(userInfo)) {
			flag = true;
			return "admin/index";
		} else {
			return ChaparkUtil.alertException(response, "회원권한이 없습니다.");
		}
	}
}
