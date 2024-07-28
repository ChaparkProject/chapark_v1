package com.springframework.chapark.controller;

import com.springframework.chapark.common.ChaparkLogger;

import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.ChaparkSecurity;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * joinController
 * 회원가입 컨트롤러
 */
@Controller
public class joinController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChaparkService chaparkService;

	@Autowired
	private ChaparkSecurity chaparkSecurity;

	/**
	 * 회원가입
	 * 
	 * @param commonMap
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/join.do")
	public void join(CommonMap commonMap, Model model, HttpServletRequest request) {
		try {
			// 비밀번호 암호화 (sha-256)
			String encryPassword = chaparkSecurity.encrypt(commonMap.get("mberPw").toString()); // map에서 가져온 비밀번호를 암호화
			commonMap.put("MBER_PW", encryPassword); // 암호화한 비밀번호 map에 다시 넣기
			// 회원가입 메서드
			chaparkService.insert("jo_join.insertMberJoin", commonMap.getMap()); // 회원가입 insert
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "join");
		}
		// return "join";
	}

	/**
	 * 아이디 중복체크
	 * @param request
	 * @param response
	 * @param commonMap
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/idCheck.do")
	public void idCheck(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap) {
		Map<String, Object> map = new HashMap();
		try {
			Map<String, Object> userInfo = chaparkService.selectMap("lo_login.selectCertificationUserInfo", commonMap.getMap());
			String mberId = "";
			if (userInfo != null) {
				mberId = (String) userInfo.get("MBER_ID"); // DB에서 사용자 아이디
			}
			String id = commonMap.get("mberId").toString(); // 사용자가 입력한 아이디
			if (mberId.trim().isEmpty() && !mberId.equals(id)) {
				map.put("result", "success");
			} else {
				map.put("result", "fail");
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "idCheck");
		}
		try { // Gson을 사용하여 맵을 JSON 문자열로 변환
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter pw = response.getWriter();

			Gson gson = new Gson();
			String data = gson.toJson(map);

			pw.write(data);
			pw.flush();
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "idCheckJson");
		}
	}
}
