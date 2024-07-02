package com.springframework.chapark.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.commonMap;
import com.springframework.chapark.common.commonUtils;
import com.springframework.chapark.utils.ChaparkUtil;

@Controller
public class MainController {

	 Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChaparkService ChaparkService;

	/**
	 * 테스트용
	 * @param model
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mber.do", method = RequestMethod.GET)
	public String mber(Model model, Map paramMap) {
		try {
			paramMap.put("mberId", "test1");
			List<Map> memberList = ChaparkService.selectList(paramMap, "tb_member.selectMberTest");
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "mberTest";
	}
	
	/**
	 * 테스트용
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		// 현재 날짜 및 시간 가져오기
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.LONG)
				.withLocale(locale);
		String formattedDate = currentDateTime.format(formatter);

		model.addAttribute("serverTime", formattedDate);

		return ChaparkUtil.getLayoutPage();
	}
	

	@RequestMapping(value = "/")
    public String home(Model model) {
		model.addAttribute("mainContent", "main.jsp");
		return "layout";
    }


}
