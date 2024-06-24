package com.springframework.chapark.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springframework.chapark.common.chaparkService;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private chaparkService chaparkService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mber.do", method = RequestMethod.GET)
	public String mber(Model model, Map paramMap) {
		try {
			Map<String, Object> member = chaparkService.selectMap("tb_member.selectMberTest", paramMap);
			model.addAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "mberTest";
	}

	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		// 현재 날짜 및 시간 가져오기
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.LONG)
				.withLocale(locale);
		String formattedDate = currentDateTime.format(formatter);

		model.addAttribute("serverTime", formattedDate);

		return "main";
	}

}
