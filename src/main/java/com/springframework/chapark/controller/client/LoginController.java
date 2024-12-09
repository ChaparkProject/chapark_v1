package com.springframework.chapark.controller.client;

import org.apache.commons.lang3.RandomStringUtils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.springframework.chapark.common.ChaparkLogger;
import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.security.CertificationService;
import com.springframework.chapark.security.ChaparkSecurity;
import com.springframework.chapark.security.SessionManagement;
import com.springframework.chapark.utils.ChaparkUtil;
import com.springframework.chapark.utils.JsonUtil;

/**
 * LoginController (로그인/ 아이디,비밀번호 찾기 컨트롤러)
 */
@RestController
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private ChaparkService chaparkService;

	@Autowired
	private ChaparkSecurity chaparkSecurity;

	private final CertificationService certificationService;

	@Autowired
	public LoginController(CertificationService certificationService) {
		this.certificationService = certificationService;
	}

	/**
	 * 로그인
	 * @param data
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody String data, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>(); //보낼 데이터 담기

		try {
			Map<String, Object> loginMap = JsonUtil.JsonToMap(data); // 받아온 Json데이터 map으로 변환

			// mberId와 mberPw 추출
			String mberId = (String) loginMap.get("mberId");
			String mberPw = (String) loginMap.get("mberPw");

			// 로그인 처리
			boolean success = certificationService.login(mberId, mberPw, loginMap);
			if (success) {
				Map<String, Object> userInfo = chaparkService.selectMap("lo_login.selectCertificationUserInfo", loginMap);
				SessionManagement.setSessionInfo(request, "userInfo", userInfo); //세션에 저장
				String mberName = (String) userInfo.get("MBER_NAME");
				SessionManagement.setSessionInfo(request, "mberName", mberName); 
				response.put("status", "success");
				response.put("data", userInfo);
				return ResponseEntity.ok(response);
			} else {
				response.put("status", "fail");
				response.put("message", "가입하지 않은 회원 입니다.");
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			logger.error("Login error", e);
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}

	/**
	 * 로그아웃
	 * @param request
	 * @return
	 */
	@PostMapping("/logout")
	public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>(); //보낼 데이터 담기

		try {
			HttpSession session = request.getSession(false); // 현재 세션을 가져옴 (없으면 null 반환)
			if (session != null) {
				session.invalidate(); // 세션 제거
			}
			response.put("status", "success");
			response.put("message", "로그아웃이 성공적으로 처리되었습니다.");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "logout");
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}

	/**
	 * 아이디 찾기
	 * @param data
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/findId")
	public  ResponseEntity<Map<String, Object>> searchId(@RequestBody String data, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>(); //보낼 데이터 담기
		try {
			Map<String, Object> findMap = JsonUtil.JsonToMap(data); // 받아온 Json데이터 map으로 변환
			Map<String, Object> userIdInfo = chaparkService.selectMap("lo_login.selectIdSearch", findMap);

			if (userIdInfo != null) {
				String mberName = userIdInfo.get("MBER_NAME").toString();
				String mberEmail = userIdInfo.get("MBER_EMAIL").toString();

				if (mberName.equals(findMap.get("mberName")) && mberEmail.equals(findMap.get("mberEmail"))) {
					response.put("status", "success");
					response.put("data", userIdInfo.get("MBER_ID"));
					return ResponseEntity.ok(response);
				} else {
					response.put("status", "fail");
					response.put("message", "회원정보가 일치하지 않습니다.");
					return ResponseEntity.ok(response);
				}
			} else {
				response.put("status", "fail");
				response.put("message", "회원정보가 없습니다.");
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "searchId");
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}
	/**
	 * 비밀번호 찾기
	 * @param data
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	@GetMapping(value = "/searchPw")
	public ResponseEntity<Map<String, Object>> searchPw(@RequestBody String data, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>(); //보낼 데이터 담기
		try {
			Map<String, Object> findMap = JsonUtil.JsonToMap(data); // 받아온 Json데이터 map으로 변환
			Map<String, Object> userPwInfo = chaparkService.selectMap("lo_login.selectPwSearch", findMap);

			if (userPwInfo != null) {
				String mberId = userPwInfo.get("MBER_ID").toString();
				String mberEmail = userPwInfo.get("MBER_EMAIL").toString();

				if (mberId.equals(findMap.get("mberId")) && mberEmail.equals(findMap.get("mberEmail"))) {
					String tempPassword = RandomStringUtils.randomAlphanumeric(10); // 임시 비밀번호 10자리 생성
					String encryPassword = chaparkSecurity.encrypt(tempPassword); // 임시 비빌번호 암호화
					findMap.put("mberPw", encryPassword);
					chaparkService.update("lo_login.tempPasswordUpdate", findMap);
					ChaparkUtil.sendPasswordEmail(mberEmail, tempPassword); // 임시 비밀번호 이메일로 전송

					response.put("status", "success");
					response.put("message", "임시 비밀번호가 발송되었습니다.");
					return ResponseEntity.ok(response);
				} else {
					response.put("status", "fail");
					response.put("message", "회원정보가 일치하지 않습니다.");
					return ResponseEntity.ok(response);
				}
			} else {
				response.put("status", "fail");
				response.put("message", "회원정보가 없습니다.");
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "searchId");
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}
}
