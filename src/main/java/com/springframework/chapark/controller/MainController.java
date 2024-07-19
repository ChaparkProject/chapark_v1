package com.springframework.chapark.controller;

import java.io.StringWriter;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.utils.ChaparkUtil;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChaparkService ChaparkService;

	/**
	 * 테스트용
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		// 현재 날짜 및 시간 가져오기
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.LONG).withLocale(locale);
		String formattedDate = currentDateTime.format(formatter);

		model.addAttribute("serverTime", formattedDate);

		return ChaparkUtil.getLayoutPage();
	}

	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("mainContent", "main.jsp");
		return "index";
	}

	@RequestMapping(value = "/changePage", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String changePage(@RequestParam("page") String page, HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/" + page);
			StringWriter stringWriter = new StringWriter();
			dispatcher.include(request, new HttpServletResponseWrapper(response) {
				@Override
				public java.io.PrintWriter getWriter() {
					return new java.io.PrintWriter(stringWriter);
				}
			});
			return stringWriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "오류가 발생했습니다.";
		}
	}

}
