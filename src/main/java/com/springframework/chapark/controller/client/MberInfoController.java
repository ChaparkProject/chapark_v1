package com.springframework.chapark.controller.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springframework.chapark.common.ChaparkLogger;
import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.SessionManagement;

@Controller
public class MberInfoController {
	
	Logger logger = LoggerFactory.getLogger(MberInfoController.class);
	
	@Autowired
	private ChaparkService chaparkService;
	
	//회원정보 접근
	@RequestMapping(value="/mberInfoAcess.do")
	public String mberInfoAcessCheck() {
		return "client/mber/mberInfoAcess";
	}
	
	//회원정보
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mberInfo.do")
	public String mberInfo(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap, Model model) {
		try {
			Map<String, Object> mberInfoCheck = chaparkService.selectMap("mb_mber.selectMberInfo", commonMap.getMap());
			
			Map<String, Object> mberInfo = (Map)SessionManagement.getSessionInfo(request, "userInfo" ); //세션에서 사용자 정보 가져오기
			model.addAttribute("mberInfo", mberInfo );
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "mberInfo");
		}
		return "client/mber/mberInfo";
	}

}
