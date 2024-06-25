package com.springframework.chapark.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springframework.chapark.common.ChaparkService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private ChaparkService ChaparkService;
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login/main.do", method = RequestMethod.GET)
	public String loginMain(Model model, Map paramMap) {
		try {
			paramMap.put("mberId", "test1");
			List<Map> memberList = ChaparkService.selectList(paramMap, "tb_member.selectMberTest");
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "loginMain";
	}
	
}
