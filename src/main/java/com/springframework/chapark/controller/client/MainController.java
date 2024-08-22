package com.springframework.chapark.controller.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springframework.chapark.common.ChaparkService;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChaparkService ChaparkService;

	@RequestMapping(value = "/main.do")
	public String home(Model model) {
		//model.addAttribute("mainContent", "main.jsp");
		return "client/common/main";
	}
}
