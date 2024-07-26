package com.springframework.chapark.controller;

import com.springframework.chapark.common.ChaparkLogger;
import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.ChaparkSecurity;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class joinController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChaparkService chaparkService;
	
	@Autowired
	private ChaparkSecurity chaparkSecurity;
	/**
	 * 회원가입
	 * @param commonMap
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/join.do")
	public void join(CommonMap commonMap, Model model, HttpServletRequest request) {
		try {
			// 암호 확인
			//System.out.println("첫번째:" + userVO.getUser_pw());
			// 비밀번호 암호화 (sha-256)
			String encryPassword = chaparkSecurity.encrypt(commonMap.get("mberPw").toString()); // map에서 가져온 비밀번호를 암호화
			commonMap.put("MBER_PW",encryPassword); //암호화한 비밀번호 map에 다시 넣기
			//System.out.println("두번째:" + commonMap.getUser_pw());
			// 회원가입 메서드
			chaparkService.insert("jo_join.insertMberJoin",commonMap.getMap()); //회원가
			// 인증 메일 보내기 메서드
			//mailsender.mailSendWithUserKey(userVO.getUser_email(), userVO.getUser_id(), request);
			

		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "join");
		}
		//return "join";
	}
	@SuppressWarnings({ "unchecked", "null" })
	@RequestMapping("/idCheck.do")
	@ResponseBody
	public Map idCheck(HttpServletRequest request, CommonMap commonMap) {
		Map <String, Object> map = new HashMap();
		try {
			Map<String, Object> userInfo = chaparkService.selectMap("lo_login.selectCertificationUserInfo", commonMap.getMap());
			String mberId = "";
			if(userInfo != null) {
				 mberId = (String) userInfo.get("MBER_ID"); // DB에서 사용자 아이디 
			}
			String id = commonMap.get("mberId").toString(); // 사용자가 입력한 아이디
			if((userInfo == null || mberId.equals("")) && !mberId.equals(id)) {
				map.put("result","success");
			} else if((mberId != null || !mberId.equals("")) && mberId.equals(id)){
				map.put("result", "fail");
			} else {
				map.put("result", "error");
				map.put("msg","오류가 발생했습니다, \n관리자에게 문의하세요.");
			}
			return map;
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "idCheck");
		}
		return map;
	}
}
