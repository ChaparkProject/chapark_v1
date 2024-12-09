package com.springframework.chapark.controller.client;

import com.springframework.chapark.common.ChaparkLogger;



import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.ChaparkSecurity;
import com.springframework.chapark.utils.ChaparkUtil;
import com.springframework.chapark.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	@PostMapping("/join")
	public  ResponseEntity<Map<String, Object>> join(@RequestBody String data, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>(); //보낼 데이터 담기
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
				return ResponseEntity.ok(response);
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
	@GetMapping("/idCheck")
	public ResponseEntity<Map<String, Object>> idCheck(@RequestParam String mberId, HttpServletRequest request) throws Exception {
		Map<String, Object> response = new HashMap(); //보낼 데이터 담기
		//Map<String, Object> chkMap = JsonUtil.JsonToMap(mberId); // 받아온 Json데이터 map으로 변환
		Map<String, Object> chkMap = new HashMap();
		chkMap.put("mberId", mberId);
		try {
			Map<String, Object> userInfo = chaparkService.selectMap("jo_join.selectMberInfo", chkMap); //회원정보 조회
			String id = "";
			if (userInfo != null) {
				id = (String) userInfo.get("MBER_ID"); 
			}
			//String id = chkMap.get("mberId").toString(); 
			if (mberId.trim().isEmpty() || !mberId.equals(id)) { //입력 받은 아이디와 DB상 아이디 불일치 => 아이디 사용가능
				response.put("status", "success");
				response.put("message", "사용 하실 수 있는 아이디 입니다.");
				return ResponseEntity.ok(response);
			} else {
				response.put("status", "fail");
				response.put("message", "이미 있는 아이디 입니다.");
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "idCheck");
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}
	
	/**
	 * 인증번호 전송
	 * @param mberEmail
	 * @param request
	 * @return
	 */
	@PostMapping("/certificationSend")
	public ResponseEntity<Map<String, Object>> certificationSend (@RequestBody String mberEmail, HttpServletRequest request) {
		Map<String, Object> response = new HashMap(); //보낼 데이터 담기
		try {
			Map<String, Object> emailMap = JsonUtil.JsonToMap(mberEmail); // 받아온 Json데이터 map으로 변환
			String email = emailMap.get("mberEmail").toString();
			if (!email.trim().isEmpty() || !email.equals("")) { //입력 받은 이메일 null체크
				String number = RandomStringUtils.randomNumeric(4);
				//입력 받은 번호 4자리 암호화
				//암호화 한 이유 : 인증번호 탈취 후 대신 가입 하는 것을 방지 => 인증번호탈취 하는 이슈를 본적 있어서 공부하는 겸 해봄 
				//추후 더 좋은 방법이 있으면 변경 예정 
				String encryptNum = ChaparkSecurity.encrypt(number); 
				request.getSession().setAttribute("certificationNumber", encryptNum); //암호화 번호 세션에 저장
				ChaparkUtil.sendcertificationEmail(email, number);
				response.put("status", "success");
				response.put("message", "인증번호 전송");
				return ResponseEntity.ok(response);
			} else {
				response.put("status", "fail");
				response.put("message", "이메일을 입력해주세요.");
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "idCheck");
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}
	
	/**
	 * 인증번호 체크
	 * @param putNumber
	 * @param request
	 * @return
	 */
	@PostMapping("/certificationCheck")
	public ResponseEntity<Map<String, Object>> certificationCheck (@RequestBody String putNumber, HttpServletRequest request) {
		Map<String, Object> response = new HashMap(); //보낼 데이터 담기
		try {
			Map<String, Object> numberMap = JsonUtil.JsonToMap(putNumber); // 받아온 Json데이터 map으로 변환
			String putNum = numberMap.get("putNumber").toString();
			if (!putNum.trim().isEmpty() || !putNum.equals("")) { //입력 받은 이메일이 있을 시
				//세션에서 암호화된 인증번호 가져오기
				String number = (String) request.getSession().getAttribute("certificationNumber");
				String mberPutNum = ChaparkSecurity.encrypt(putNum);
				if (mberPutNum.equals(number) ) { //비교
					response.put("status", "success");
					response.put("message", "인증번호 일치");
					return ResponseEntity.ok(response);
				} else {
					response.put("status", "fail");
					response.put("message", "인증번호가 다릅니다.");
					return ResponseEntity.ok(response);
				}
			} else {
				response.put("status", "fail");
				response.put("message", "이메일을 입력해주세요.");
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "idCheck");
			response.put("status", "error");
			response.put("message", "서버 에러가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //500 (서버 내부 오류)
		}
	}
}
