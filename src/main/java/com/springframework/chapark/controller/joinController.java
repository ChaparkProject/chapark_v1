package com.springframework.chapark.controller;

import com.springframework.chapark.common.ChaparkLogger;
import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;

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


	@GetMapping("/join.do")
	public String joinForm() {
		return "join";
	}

	@PostMapping("/join.do")
	public String join(CommonMap commonMap, Model model) {
		try {
			chaparkService.insert("jo_join.insertMberJoin",commonMap.getMap());

		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "join");
		}
		return "join";
	}
}
