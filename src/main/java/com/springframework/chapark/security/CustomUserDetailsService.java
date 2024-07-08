package com.springframework.chapark.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private static ChaparkService chaparkService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		throw new UnsupportedOperationException(
				"Method not supported. Use loadUserByUsername(String username, CommonMap commonMap, HttpServletRequest request) instead.");
	}

	@SuppressWarnings("unchecked")
	public static CustomUserDetails loadUserByUsername(String username, CommonMap commonMap, HttpServletRequest request)
			throws UsernameNotFoundException {
		try {
			// 사용자 정보 조회
			Map<String, Object> memberInfo = chaparkService.selectMap("lo_login.selectSecurityUserInfo", commonMap.getMap());

			if (memberInfo != null && !memberInfo.isEmpty()) {
				// 사용자 정보를 CustomUserDetails 객체에 매핑
				CustomUserDetails userDetails = new CustomUserDetails();
				userDetails.setUsername((String) memberInfo.get("MBER_ID"));
				userDetails.setPassword((String) memberInfo.get("MBER_PW"));
				userDetails.setName((String) memberInfo.get("MBER_NAME"));
				userDetails.setRole((String) memberInfo.get("MBER_AUTH"));
				userDetails.setEnabled((Boolean) memberInfo.get("ENABLED"));

				// 세션에 사용자 정보를 저장
				if (request != null) {
					request.getSession().setAttribute("userDetails",userDetails);
				}
				
				return userDetails;
			} else {
				throw new UsernameNotFoundException("오류가 발생했습니다. 사용자 이름: " + username);
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("사용자 정보를 검색하는 중 오류가 발생했습니다.", e);
		}
	}
}
