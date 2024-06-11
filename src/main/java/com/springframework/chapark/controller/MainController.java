package com.springframework.chapark.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/main.do")
	public String main() {

		return "main";
	}

}
