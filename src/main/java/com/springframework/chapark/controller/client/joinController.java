package com.springframework.chapark.controller.client;

import com.springframework.chapark.common.ChaparkLogger;

import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.ChaparkSecurity;
import com.springframework.chapark.utils.JsonUtil;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * joinController (회원가입 컨트롤러)
 */
@RestController
public class joinController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChaparkService chaparkService;

	@Autowired
	private ChaparkSecurity chaparkSecurity;


	/**
	 * 회원가입
	 * @param data
	 * @param request
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/join")
	public  ResponseEntity<Map<String, Object>> join(@RequestBody String data, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			Map<String, Object> joinMap = JsonUtil.JsonToMap(data); // 받아온 Json데이터 map으로 변환
			// 비밀번호 암호화
			String mberPw = joinMap.get("mberPw") != null ? joinMap.get("mberPw").toString() : "";
			if (!mberPw.isEmpty()) {
				//비밀번호 암호화
				String encryPassword = chaparkSecurity.encrypt(mberPw);
				
				joinMap.put("mberPw", encryPassword);
				joinMap.put("mberAuth", "C"); // 기본 권한 c로 설정
				chaparkService.insert("jo_join.insertMberJoin", joinMap); //회원가입(insert)
				response.put("status", "success");
				response.put("message", "회원가입이 완료되었습니다.");
				return ResponseEntity.ok(response);
			} else {
				response.put("status", "fail");
				response.put("message", "잘못된 요청입니다.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); //400 (잘못된 요청)
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "join");
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}

	/**
	 * 아이디 중복체크
	 * @param data
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/idCheck")
	public ResponseEntity<Map<String, Object>> idCheck(@RequestBody String data, HttpServletRequest request) throws Exception {
		Map<String, Object> response = new HashMap();
		Map<String, Object> chkMap = JsonUtil.JsonToMap(data); // 받아온 Json데이터 map으로 변환
		try {
			Map<String, Object> userInfo = chaparkService.selectMap("jo_join.selectMberInfo", chkMap); //회원정보 조회
			String mberId = "";
			if (userInfo != null) {
				mberId = (String) userInfo.get("MBER_ID"); 
			}
			String id = chkMap.get("mberId").toString(); 
			if (mberId.trim().isEmpty() && !mberId.equals(id)) { //입력 받은 아이디와 DB상 아이디 불일치 => 아이디 사용가능
				response.put("status", "success");
				response.put("message", "사용 하실 수 있는 아이디 입니다.");
				return ResponseEntity.ok(response);
			} else {
				response.put("status", "fail");
				response.put("message", "이미 있는 아이디 입니다.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); //400 (잘못된 요청)
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "idCheck");
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}
}
