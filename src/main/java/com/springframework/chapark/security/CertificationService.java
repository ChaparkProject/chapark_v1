package com.springframework.chapark.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springframework.chapark.common.ChaparkLogger;
import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
import java.util.Map;

@Service
public class CertificationService {

	@Autowired
	private ChaparkService chaparkService;

	@Autowired
	private ChaparkSecurity chaparkSecurity;

	@SuppressWarnings({ "unchecked", "static-access" })
	public boolean login(String mberId, String mberPw) {
		CommonMap commonMap = new CommonMap();
		commonMap.put("mberId", mberId);

		try {
			Map<String, Object> userInfo = chaparkService.selectMap("lo_login.selectCertificationUserInfo", commonMap.getMap());
			if (userInfo != null) {
				String storedHashedPassword = (String) userInfo.get("MBER_PW");
				String inputHashedPassword = chaparkSecurity.encrypt(mberPw);
				return storedHashedPassword.equals(inputHashedPassword);
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "join");
		}
		return false;
	}
}

