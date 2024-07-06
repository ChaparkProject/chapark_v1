package com.springframework.chapark.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;



public class UserDetails {
	
	private String MBER_ID;
	private String MBER_PW;
	private String MBER_NAME;
	private String MBER_AUTH;
	private boolean ENABLED;
	
	
	   Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	   String getPassword() {
		return MBER_PW;
	}

	   String getUsername() {
		return MBER_NAME;
	}

	   boolean isAccountNonExpired() {
		return true;
	}

	   boolean isAccountNonLocked() {
		return true;
	}

	   boolean isCredentialsNonExpired() {
		return true;
	}

	   boolean isEnabled() {
		return ENABLED;
	}
	   String setNAME() {
		return MBER_NAME;
	}
}
