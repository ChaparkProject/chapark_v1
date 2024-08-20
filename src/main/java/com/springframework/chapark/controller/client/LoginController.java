package com.springframework.chapark.controller.client;

import org.apache.commons.lang3.RandomStringUtils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.springframework.chapark.common.ChaparkLogger;
import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
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

	private final ObjectMapper objectMapper = new ObjectMapper(); // JSON 처리용 ObjectMapper
	
	private final TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody String data, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();

		try {
			// 리액트에서 받아온 Json데이터 map으로 변환
			Map<String, Object> loginMap = JsonUtil.JsonToMap(data);

			// mberId와 mberPw 추출
			String mberId = (String) loginMap.get("id");
			String mberPw = (String) loginMap.get("pw");

			// 로그인 처리
			boolean success = certificationService.login(mberId, mberPw, loginMap);
			if (success) {
				Map<String, Object> userInfo = chaparkService.selectMap("lo_login.selectCertificationUserInfo", loginMap);
				SessionManagement.setSessionInfo(request, "userInfo", userInfo);
				String mberName = (String) userInfo.get("MBER_NAME");
				SessionManagement.setSessionInfo(request, "mberName", mberName);

				response.put("status", "success");
				response.put("userInfo", userInfo);
				return ResponseEntity.ok(response);
			} else {
				response.put("status", "error");
				response.put("message", "아이디와 비밀번호가 일치하지 않습니다.");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}
		} catch (Exception e) {
			logger.error("Login error", e);
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/**
	 * 로그아웃
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/logout")
	public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();

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
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/**************************** 수정 전 *******************************************/

	/**
	 * 아이디 찾기 페이지
	 * 
	 * @return
	 */
	@RequestMapping(value = "/searchIdPage.do")
	public String searchIdPage() {
		return "client/mber/idSearch";
	}

	/**
	 * 아이디 찾기
	 * 
	 * @param request
	 * @param response
	 * @param commonMap
	 * @param model
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchId.do")
	public void searchId(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap, Model model) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Map<String, Object> userIdInfo = chaparkService.selectMap("lo_login.selectIdSearch", commonMap.getMap());

			if (userIdInfo != null) {
				String mberName = userIdInfo.get("MBER_NAME").toString();
				String mberTel = userIdInfo.get("MBER_TEL").toString();

				if (mberName.equals(commonMap.get("mberName")) && mberTel.equals(commonMap.get("mberTel"))) {
					resultMap.put("result", "true");
					resultMap.put("mberId", userIdInfo.get("MBER_ID"));
				} else {
					resultMap.put("result", "false");
				}
			} else {
				resultMap.put("result", "false");
				;
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "searchId");
		}
		Gson gson = new Gson();
		PrintWriter pw = null;
		String json = "";
		try {// Gson을 사용하여 맵을 JSON 문자열로 변환
			response.setContentType("application/json;charset=UTF-8");
			json = gson.toJson(resultMap);
			pw = response.getWriter();
			pw.write(json);
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "searchId");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * 비밀번호 찾기 페이지
	 * 
	 * @return
	 */
	@RequestMapping(value = "/searchPwPage.do")
	public String searchPwPage() {
		return "client/mber/pwSearch";
	}

	/**
	 * 비밀번호 찾기
	 * 
	 * @param request
	 * @param response
	 * @param commonMap
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	@RequestMapping(value = "/searchPw.do")
	public void searchPw(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Map<String, Object> userPwInfo = chaparkService.selectMap("lo_login.selectPwSearch", commonMap.getMap());

			if (userPwInfo != null) {
				String mberId = userPwInfo.get("MBER_ID").toString();
				String mberEmail = userPwInfo.get("MBER_EMAIL").toString();

				if (mberId.equals(commonMap.get("mberId")) && mberEmail.equals(commonMap.get("mberEmail"))) {
					String tempPassword = RandomStringUtils.randomAlphanumeric(10); // 임시 비밀번호 10자리 생성
					String encryPassword = chaparkSecurity.encrypt(tempPassword); // 임시 비빌번호 암호화
					commonMap.put("mberPw", encryPassword);
					chaparkService.update("lo_login.tempPasswordUpdate", commonMap.getMap());
					ChaparkUtil.sendEmail(mberEmail, tempPassword); // 임시 비밀번호 이메일로 전송

					resultMap.put("result", "true");
				} else {
					resultMap.put("result", "false");
				}
			} else {
				resultMap.put("result", "false");
				;
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "searchId");
		}
		Gson gson = new Gson();
		PrintWriter pw = null;
		String json = "";
		try {// Gson을 사용하여 맵을 JSON 문자열로 변환
			response.setContentType("application/json;charset=UTF-8");
			json = gson.toJson(resultMap);
			pw = response.getWriter();
			pw.write(json);
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "searchId");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
}
