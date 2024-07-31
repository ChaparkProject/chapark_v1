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
import com.springframework.chapark.security.ChaparkAuthor;
import com.springframework.chapark.security.ChaparkSecurity;
import com.springframework.chapark.security.SessionManagement;
import com.springframework.chapark.utils.ChaparkUtil;

@Controller
public class AdminMainController {
	
	@Autowired
	private  ChaparkService chaparkService;
	
	@RequestMapping(value = "/admin/main.do")
	public String adminMain(Model model, CommonMap commonMap, HttpServletResponse response, HttpServletRequest request) {
		
		Map userInfo =(Map) SessionManagement.getSessionInfo(request,"");
		Boolean flag = false;

		if(ChaparkAuthor.authorCheck(userInfo)) {
			flag = true;
			return "admin/common/index";
		} else {
			return ChaparkUtil.alertException(response, "회원권한이 없습니다.");
		}
	}
}
