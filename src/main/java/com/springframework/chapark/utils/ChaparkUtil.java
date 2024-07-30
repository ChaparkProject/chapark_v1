package com.springframework.chapark.utils;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;


/**
 * 공통유틸리티
 */
@Component
public class ChaparkUtil {
	// 회원 권한 체크
	public static Boolean authorCheck(Map userInfo) {
		if(userInfo != null) {
			String auth = (String) userInfo.get("MBER_AUTH");
			if(auth.equals("A")) {
				return true;
			}
		}
		return false ;
	}
	// 세션 회원 정보 
	public static Map sessionUserInfo(HttpSession session) {
		
		Map userInfo = null;
		if(session != null) {
			userInfo = (Map) session.getAttribute("userInfo");
		}
		return userInfo;
	}

}
