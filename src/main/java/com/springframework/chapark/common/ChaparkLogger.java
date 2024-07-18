package com.springframework.chapark.common;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ChaparkLogger {
	 static Logger logger = LoggerFactory.getLogger(ChaparkLogger.class);
	
	public static void debug(Exception e,  Class c, String method) {
		logger.debug("chapark" + c.getName() + method + e.getClass().getName());
	}
}
