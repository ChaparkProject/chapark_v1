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
import org.springframework.web.bind.annotation.RequestMethod;

import com.springframework.chapark.common.ChaparkLogger;
import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.ChaparkSecurity;
import com.springframework.chapark.security.SessionManagement;
import com.springframework.chapark.utils.ChaparkUtil;

@Controller
public class MberInfoController {
	
	Logger logger = LoggerFactory.getLogger(MberInfoController.class);
	
	@Autowired
	private ChaparkService chaparkService;
	
	@Autowired
	private ChaparkSecurity chaparkSecurity;
	
	//회원정보 접근
	@RequestMapping(value="/mberInfoAcess.do", method = RequestMethod.GET)
	public String mberInfoAcessPage() {
		return "client/mber/mberInfoAcess";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/mberInfoAcess.do", method = RequestMethod.POST)
	public String mberInfoAcessCheck(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap, Model model) {
		
		try {
			String mberPw = commonMap.get("mberPw").toString();
			String encryPassword = chaparkSecurity.encrypt(mberPw); //입력받은 비밀번호 암호화
			commonMap.put("mberPw",encryPassword); // 다시 commonMap에 넣기
			Map<String, Object> mberInfoCheck = chaparkService.selectMap("mb_mber.selectMberInfo", commonMap.getMap());
			
			if(mberInfoCheck != null) {
				return "redirect:/mberInfo.do";
			} else {
				model.addAttribute("error", "비밀번호가 일치하지 않습니다." );
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "mberInfoAcessCheck");
		}
		
		return "client/mber/mberInfoAcess";
	}
	
	//회원정보
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mberInfo.do")
	public String mberInfo(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap, Model model) {
		try {
				Map<String, Object> mberInfo = (Map)SessionManagement.getSessionInfo(request, "userInfo" ); //세션에서 사용자 정보 가져오기
				model.addAttribute("mberInfo", mberInfo );
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "mberInfo");
		}
		return "client/mber/mberInfo";
	}
	
	public String mberInfoPwChange() {
		return "";
	}

}
