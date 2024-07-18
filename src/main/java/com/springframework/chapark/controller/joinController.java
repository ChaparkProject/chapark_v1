package com.springframework.chapark.controller;

import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class joinController {

	@Autowired
	private ChaparkService chaparkService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/join")
	public String joinForm() {
		return "join";
	}

	@PostMapping("/join")
	public void join(CommonMap commonMap, Model model) {
		try {
			// 비밀번호 암호화
			String encryptedPassword = passwordEncoder.encode((String) commonMap.get("password"));

			// 회원 정보 저장
			commonMap.put("password", encryptedPassword);
			chaparkService.insert("tb_member.insertMember",commonMap.getMap());

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("signupError", true);
		}
	}
}
