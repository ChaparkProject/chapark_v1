package com.springframework.chapark.security;

import java.util.Map;

public class ChaparkAuthor {
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
}
