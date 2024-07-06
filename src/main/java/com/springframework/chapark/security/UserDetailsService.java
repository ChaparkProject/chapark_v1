package com.springframework.chapark.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsService implements UserDetails{
	
	private String MBER_ID;
	private String MBER_PW;
	private String MBER_NAME;
	private String MBER_AUTH;
	private boolean ENABLED;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority("ROLE_" + MBER_AUTH));
		return auth;
	}
	
	@Override
	public String getPassword() {
		return MBER_PW;
	}
	
	@Override
	public String getUsername() {
		return MBER_ID;
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
		return ENABLED;
	}
	
	public String getNAME() {
		return MBER_NAME;
	}

	public void setNAME(String name) {
		MBER_NAME = name;
	}

}
