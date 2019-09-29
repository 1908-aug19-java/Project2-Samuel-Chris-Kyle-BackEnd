package com.revature.gamesgalore.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private SecurityHandler securityHandler;
	
	JwtConfigurer(SecurityHandler securityHandler){
		this.securityHandler = securityHandler;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new JwtTokenFilter(securityHandler), UsernamePasswordAuthenticationFilter.class);
	}
}
