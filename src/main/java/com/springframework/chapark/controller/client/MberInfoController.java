package com.springframework.chapark.controller.client;

import java.io.PrintWriter;
import java.util.HashMap;
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

import com.google.gson.Gson;
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
	
	/**
	 * 회원정보 접근
	 * @return
	 */
	@RequestMapping(value="/mberInfoAcess.do", method = RequestMethod.GET)
	public String mberInfoAcessPage(HttpServletRequest request, HttpServletResponse response) {
		
		//세션에서 사용자 정보 가져오기
		Map<String, Object> mberInfo = (Map)SessionManagement.getSessionInfo(request, "userInfo" ); 
		if(mberInfo == null) {
			ChaparkUtil.alertUrlException(response, "사용자 정보가 없습니다.", "/main.do");
		}
		return "client/mber/mberInfoAcess";
	}
	
	/**
	 * 회원정보접근(비밀번호 체크)
	 * @param request
	 * @param response
	 * @param commonMap
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/mberInfoAcess.do", method = RequestMethod.POST)
	public String mberInfoAcessCheck(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap, Model model) {
		
		//세션에서 사용자 정보 가져오기
		Map<String, Object> mberInfo = (Map)SessionManagement.getSessionInfo(request, "userInfo" ); 
		if(mberInfo == null) {
			ChaparkUtil.alertUrlException(response, "사용자 정보가 없습니다.", "/main.do");
		}
		
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
	
	/**
	 * 회원정보수정페이지
	 * @param request
	 * @param response
	 * @param commonMap
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mberInfo.do")
	public String mberInfoPage(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap, Model model) {
		try {
				Map<String, Object> mberInfo = (Map)SessionManagement.getSessionInfo(request, "userInfo" ); //세션에서 사용자 정보 가져오기
				if(mberInfo != null) {
					model.addAttribute("mberInfo", mberInfo );
				} else {
					ChaparkUtil.alertUrlException(response, "사용자 정보가 없습니다.", "/main.do");
				}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "mberInfoPage");
		}
		return "client/mber/mberInfo";
	}
	
	/**
	 * 비밂번호 변경
	 * @return
	 */
	@RequestMapping(value = "/updateMberPw.do")
	public String updateMberInfoPw(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap) {
		Map<String, Object> mberInfo = (Map)SessionManagement.getSessionInfo(request, "userInfo" ); //세션에서 사용자 정보 가져오기
		if(mberInfo == null) {
			ChaparkUtil.alertUrlException(response, "사용자 정보가 없습니다.", "/main.do");
		}
		return "client/mber/updateMberPw";
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@RequestMapping(value = "/updatePassword.do")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap, Model model) {
		Map<String, Object> map = new HashMap();
		Map<String, Object> pwMap = new HashMap();
		
		Map<String, Object> mberInfo = (Map)SessionManagement.getSessionInfo(request, "userInfo" ); //세션에서 사용자 정보 가져오기
		if(mberInfo == null) {
			ChaparkUtil.alertUrlException(response, "사용자 정보가 없습니다.", "/main.do");
		}
		
		try {
			String mberPw = commonMap.get("mberPw").toString(); //기존 비밀번호
			String newMberPw = commonMap.get("newMberPw").toString(); //새로운 비밀번호
			String checkNewPw = commonMap.get("checkNewPw").toString(); //새로운 비밀번호 확인
			
			String encryPassword = chaparkSecurity.encrypt(mberPw); //입력받은 비밀번호 암호화
			map.put("mberPw", encryPassword); //현재 비밀번호 조건걸기 위함
			Map<String, Object> mberPwCheck = chaparkService.selectMap("mb_mber.selectMberInfo", map); //사용자가 있는지 없는지 체크
			
			if(mberPwCheck != null) {
				if(!mberPw.equals(newMberPw)) {
					if (newMberPw.equals(checkNewPw)) {
						String encrytPassword = chaparkSecurity.encrypt(newMberPw); //입력받은 비밀번호 암호화
						map.put("mberId", mberPwCheck.get("MBER_ID"));
						map.put("newMberPw", encrytPassword);
						chaparkService.update("mb_mber.updateMberPassword",map);
						pwMap.put("result", "update");
					} else {
						pwMap.put("result", "A");
					}
				} else {
					pwMap.put("result", "B");
				}
			} else {
				pwMap.put("result", "C");
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "updatePassword");
		}
		Gson gson = new Gson();
		PrintWriter pw = null;
		String json = "";
		try {// Gson을 사용하여 맵을 JSON 문자열로 변환
			response.setContentType("application/json;charset=UTF-8");
			json = gson.toJson(pwMap);
			pw = response.getWriter();
			pw.write(json);
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "idCheckJson");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
}
