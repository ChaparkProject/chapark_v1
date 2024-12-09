package com.springframework.chapark.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.springframework.chapark.common.ChaparkLogger;
import com.springframework.chapark.common.ChaparkService;
import java.util.Map;

@Service
public class CertificationService {

	@Autowired
	private ChaparkService chaparkService;

	@Autowired
	private ChaparkSecurity chaparkSecurity;

	/**
	 * 사용자 인증
	 * @param mberId
	 * @param mberPw
	 * @param commonMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public boolean login(String mberId, String mberPw, Map map) {
		try {
			//DB에서 사용자 정보 가져오기
			Map<String, Object> userInfo = chaparkService.selectMap("lo_login.selectCertificationUserInfo", map);
			if (userInfo != null) {
				String storedHashedPassword = (String) userInfo.get("mberPw"); //암호화된 비밀번호 가져오기
				String inputHashedPassword = chaparkSecurity.encrypt(mberPw); //입력받은 비밀번호 암호화
				return storedHashedPassword.equals(inputHashedPassword); //비교
			}
		} catch (Exception e) {
			ChaparkLogger.debug(e, this.getClass(), "login");
		}
		return false;
	}
}

