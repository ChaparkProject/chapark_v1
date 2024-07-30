package com.springframework.chapark.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.springframework.chapark.common.ChaparkLogger;


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
	
	
	// 예외처리
	public static String alertException(HttpServletResponse response,  String message, String url) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println("<html><body>");
			out.println("<script>");
			out.println("alert('"+ message +"');");
			out.println("location.href = '"+ url +"';");
			out.println("</script>");
			out.println("</body></html>");
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (out != null) {
				out.flush(); 
				//out.close(); 
			}
		}
		return null;
	}
	
    private static String escapeJavaScript(String input) {
        if (input == null) {
            return "";
        }
        return input
                .replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\b", "\\b")
                .replace("\f", "\\f");
    }

}
