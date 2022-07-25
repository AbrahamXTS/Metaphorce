package com.metaphorce.rrhh.security;

import lombok.*;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.metaphorce.rrhh.models.User;

@Getter
@AllArgsConstructor
// Represents the authenticated user for Spring security
public class PrincipalUser implements UserDetails {
	
	private User user;
	private Collection<? extends GrantedAuthority> authorities;
	
	public static PrincipalUser create(User user) {
		return new PrincipalUser(user, Collections.singletonList(new SimpleGrantedAuthority("GENERIC")));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
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
		return true;
	}
	
	public static User getCurrentUser() {
		PrincipalUser principal = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal.getUser();
	}
}