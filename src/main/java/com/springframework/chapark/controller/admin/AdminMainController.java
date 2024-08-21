package com.springframework.chapark.controller.admin;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.security.ChaparkAuthor;
import com.springframework.chapark.security.SessionManagement;

@RestController
public class AdminMainController {
	
	@Autowired
	private  ChaparkService chaparkService;
	
	@PostMapping(value = "/admin/main")
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
