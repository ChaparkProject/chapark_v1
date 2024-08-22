package com.springframework.chapark.controller.client;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.chapark.common.ChaparkLogger;
import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.ChaparkSecurity;
import com.springframework.chapark.security.SessionManagement;
import com.springframework.chapark.utils.ChaparkUtil;
import com.springframework.chapark.utils.JsonUtil;

@RestController
public class MberInfoController {
	
	Logger logger = LoggerFactory.getLogger(MberInfoController.class);
	
	@Autowired
	private ChaparkService chaparkService;
	
	@Autowired
	private ChaparkSecurity chaparkSecurity;
	
	
	/**
	 * 회원정보접근
	 * @param data
	 * @param request
	 * @return
	 */
	@GetMapping(value="/mberInfoAcess")
	public ResponseEntity<Map<String, Object>> mberInfoAcess(@RequestBody String data, HttpServletRequest request) {
		Map<String, Object> response = new HashMap(); //보낼 데이터 담기
		
		//세션에서 사용자 정보 가져오기
		Map<String, Object> mberInfo = (Map)SessionManagement.getSessionInfo(request, "userInfo" ); 
		
		//사용자 정보 체크
		if(mberInfo == null) {
			response.put("status", "fail");
			response.put("message", "사용자 정보가 없습니다. 로그인 해주세요.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); //400 (잘못된 요청)
		}
		
		// 회원정보접근 체크
		try {
			Map<String, Object> acsMap = JsonUtil.JsonToMap(data); // 받아온 Json데이터 map으로 변환
			String mberPw = acsMap.get("mberPw").toString();
			String encryPassword = chaparkSecurity.encrypt(mberPw); //입력받은 비밀번호 암호화
			acsMap.put("mberPw",encryPassword); // 다시 commonMap에 넣기
			Map<String, Object> mberInfoCheck = chaparkService.selectMap("mb_mber.selectMberInfo", acsMap);
			
			if(mberInfoCheck != null) {
				response.put("status", "success");
				return ResponseEntity.ok(response);
			} else {
				response.put("status", "fail");
				response.put("message", "비밀번호가 일치하지 않습니다.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); //400 (잘못된 요청)
			}
		} catch (Exception e) {
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}
	
	/**
	 * 회원정보수정페이지
	 * @param request
	 * @param response
	 * @param commonMap
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/mberInfo")
	public ResponseEntity<Map<String, Object>> mberInfoPage(@RequestBody String data, HttpServletRequest request) {
		Map<String, Object> response = new HashMap(); //보낼 데이터 담기
		try {
			Map<String, Object> acsMap = JsonUtil.JsonToMap(data); // 받아온 Json데이터 map으로 변환
			Map<String, Object> mberInfo = (Map)SessionManagement.getSessionInfo(request, "userInfo" ); //세션에서 사용자 정보 가져오기
			
			//세션에 사용자 정보가 있는지 확인
			if(mberInfo != null) {
				response.put("status", "success");
				return ResponseEntity.ok(response);
			} else {
				response.put("status", "fail");
				response.put("message", "사용자 정보가 없습니다. 로그인 해주세요.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); //400 (잘못된 요청)
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "mberInfoPage");
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}
	
	/**
	 * 비밀번호 변경
	 * @param data
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	@PatchMapping(value = "/updatePassword")
	public ResponseEntity<Map<String, Object>> updatePassword(@RequestBody String data, HttpServletRequest request) {
		Map<String, Object> response = new HashMap(); //보낼 데이터 담기
		Map<String, Object> map = new HashMap();
		
		Map<String, Object> mberInfo = (Map)SessionManagement.getSessionInfo(request, "userInfo" ); //세션에서 사용자 정보 가져오기
		if(mberInfo == null) {
			response.put("status", "fail");
			response.put("message", "사용자 정보가 없습니다. 로그인 해주세요.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); //400 (잘못된 요청)
		}
		
		try {
			Map<String, Object> pwMap = JsonUtil.JsonToMap(data);
			String mberPw = pwMap.get("mberPw").toString(); //기존 비밀번호
			String newMberPw = pwMap.get("newMberPw").toString(); //새로운 비밀번호
			String checkNewPw = pwMap.get("checkNewPw").toString(); //새로운 비밀번호 확인
			
			String encryPassword = chaparkSecurity.encrypt(mberPw); //입력받은 비밀번호 암호화
			map.put("mberPw", encryPassword); //현재 비밀번호 조건걸기 위함
			Map<String, Object> mberPwCheck = chaparkService.selectMap("mb_mber.selectMberInfo", map); //사용자가 있는지 없는지 체크
			
			if(mberPwCheck != null) {
				if(!mberPw.equals(newMberPw)) {
					if (newMberPw.equals(checkNewPw)) {
						String encrytPassword = chaparkSecurity.encrypt(newMberPw); //입력받은 비밀번호 암호화
						map.put("mberId", mberPwCheck.get("MBER_ID"));
						map.put("newMberPw", encrytPassword);
						chaparkService.update("mb_mber.updateMberPassword",map);
						response.put("status", "success");
						return ResponseEntity.ok(response);
					} else {
						response.put("status", "fail");
						response.put("message", "비밀번호가 일치 하지 않습니다.");
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); //400 (잘못된 요청)
					}
				} else {
					response.put("status", "fail");
					response.put("message", "이미 사용중인 비밀번호 입니다.");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); //400 (잘못된 요청)
				}
			} else {
				response.put("status", "fail");
				response.put("message", "가입하지 않은 회원입니다.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); //400 (잘못된 요청)
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "updatePassword");
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}
}
