package com.metaphorce.rrhh.security;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.metaphorce.rrhh.models.User;
import com.metaphorce.rrhh.services.UsersService;
import com.metaphorce.rrhh.repositories.UsersRepository;
import com.metaphorce.rrhh.exceptions.NonExistException;

// For each request
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UsersService userService;

	@Autowired
	private UsersRepository userRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		try {
			String jwt = getJWTFromRequest(request);

			if (StringUtils.hasText(jwt) && userService.validateToken(jwt)) {
				String username = userService.getUsernameFromToken(jwt);

				User user = userRepo.findByUsername(username)
						.orElseThrow(() -> new NonExistException("The user doesn't exist."));

				PrincipalUser principal = PrincipalUser.create(user);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal,null, principal.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			log.error("Failed to authenticate user", e);
		}
		filterChain.doFilter(request, response);
	}

	public String getJWTFromRequest(HttpServletRequest request) {

		String bearerToken = request.getHeader("Authorization");
        
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}