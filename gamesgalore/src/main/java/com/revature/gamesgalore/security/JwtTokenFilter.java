package com.revature.gamesgalore.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean {

	private SecurityHandler securityHandler;

	JwtTokenFilter(SecurityHandler securityHandler) {
		this.securityHandler = securityHandler;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse reponse, FilterChain filterChain)
			throws IOException, ServletException {
		String jwt = securityHandler.resolveToken((HttpServletRequest) request);
		if (jwt != null && securityHandler.isNotExpired(jwt)) {
			Authentication auth = securityHandler.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, reponse);
	}

	

}
