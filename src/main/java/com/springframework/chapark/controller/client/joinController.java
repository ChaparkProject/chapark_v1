package com.springframework.chapark.controller.client;

import com.springframework.chapark.common.ChaparkLogger;

import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import com.springframework.chapark.security.ChaparkSecurity;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * joinController ȸ������ ��Ʈ�ѷ�
 */
@Controller
public class joinController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChaparkService chaparkService;

	@Autowired
	private ChaparkSecurity chaparkSecurity;

	/**
	 * ȸ������
	 * 
	 * @param commonMap
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/join.do")
	public void join(CommonMap commonMap, Model model, HttpServletRequest request) {
		try {
			// ��й�ȣ ��ȣȭ (sha-256)
			String encryPassword = chaparkSecurity.encrypt(commonMap.get("mberPw").toString()); // map���� ������
																								// ��й�ȣ�� ��ȣȭ
			commonMap.put("MBER_PW", encryPassword); // ��ȣȭ�� ��й�ȣ map�� �ٽ� �ֱ�
			commonMap.put("MBER_AUTH", "C"); // ��ȣȭ�� ��й�ȣ map�� �ٽ� �ֱ�
			
			// ȸ������ �޼���
			chaparkService.insert("jo_join.insertMberJoin", commonMap.getMap()); // ȸ������ insert
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "join");
		}
		// return "join";
	}

	/**
	 * ���̵� �ߺ�üũ
	 * 
	 * @param request
	 * @param response
	 * @param commonMap
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/idCheck.do")
	public void idCheck(HttpServletRequest request, HttpServletResponse response, CommonMap commonMap) {
		Map<String, Object> map = new HashMap();
		try {
			Map<String, Object> userInfo = chaparkService.selectMap("lo_login.selectCertificationUserInfo",
					commonMap.getMap());
			String mberId = "";
			if (userInfo != null) {
				mberId = (String) userInfo.get("MBER_ID"); // DB���� ����� ���̵�
			}
			String id = commonMap.get("mberId").toString(); // ����ڰ� �Է��� ���̵�
			if (mberId.trim().isEmpty() && !mberId.equals(id)) {
				map.put("result", "success");
			} else {
				map.put("result", "fail");
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "idCheck");
		}
		Gson gson = new Gson();
		PrintWriter pw = null;
		String json = "";
		try {// Gson을 사용하여 맵을 JSON 문자열로 변환
			response.setContentType("application/json;charset=UTF-8");
			json = gson.toJson(map);
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
