package com.springframework.chapark.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Map<String, Object>> adminMain(@RequestBody String data, HttpServletRequest request) {
		
		Map<String, Object> response = new HashMap(); //보낼 데이터 담기
		Map userInfo =(Map) SessionManagement.getSessionInfo(request,"userInfo");
		Boolean flag = false;

		if(ChaparkAuthor.authorCheck(userInfo)) {
			flag = true;
			response.put("status", "success");
			response.put("message", "관리자권한 성공!");
			return ResponseEntity.ok(response);
		} else {
			response.put("status", "fail");
			response.put("message", "회원권한이 없습니다.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); //400 (잘못된 요청)
		}
	}
}
