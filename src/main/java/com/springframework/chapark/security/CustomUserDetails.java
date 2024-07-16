package com.springframework.chapark.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {

	private String MBER_ID;
	private String MBER_PW;
	private String MBER_NAME;
	private String MBER_AUTH;
	private boolean enabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority("ROLE_" + MBER_AUTH));
		return auth;
	}

	@Override
	public String getPassword() {
		return MBER_PW;
	}

	@Override
	public String getUsername() {
		return MBER_ID; // 사용자 ID로 설정
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getName() {
		return MBER_NAME;
	}

	public void setName(String MBER_NAME) {
		this.MBER_NAME = MBER_NAME;
	}

	public void setUsername(String MBER_ID) {
		this.MBER_ID = MBER_ID; // 사용자 ID로 설정
	}

	public void setPassword(String MBER_PW) {
		this.MBER_PW = MBER_PW;
	}

	public void setRole(String mberAuth) {
		this.MBER_AUTH = mberAuth;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
