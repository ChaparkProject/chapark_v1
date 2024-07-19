package com.springframework.chapark.controller;

import com.springframework.chapark.common.ChaparkLogger;
import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.Encryption;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class joinController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChaparkService chaparkService;
	
	@Autowired
	private Encryption encryption;


	@GetMapping("/join.do")
	public String joinForm() {
		return "join";
	}

	@SuppressWarnings("static-access")
	@PostMapping("/join.do")
	public String join(CommonMap commonMap, Model model, HttpServletRequest request) {
		try {
			// 암호 확인
			//System.out.println("첫번째:" + userVO.getUser_pw());
			// 비밀번호 암호화 (sha-256)
			String encryPassword = encryption.encrypt(commonMap.get("MBER_PW").toString()); // map에서 가져온 비밀번호를 암호화
			commonMap.put("MBER_PW",encryPassword); //암호화한 비밀번호 map에 다시 넣기
			//System.out.println("두번째:" + commonMap.getUser_pw());
			// 회원가입 메서드
			chaparkService.insert("jo_join.insertMberJoin",commonMap.getMap()); //회원가입 insert
			// 인증 메일 보내기 메서드
			//mailsender.mailSendWithUserKey(userVO.getUser_email(), userVO.getUser_id(), request);
			

		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "join");
		}
		return "join";
	}
}
