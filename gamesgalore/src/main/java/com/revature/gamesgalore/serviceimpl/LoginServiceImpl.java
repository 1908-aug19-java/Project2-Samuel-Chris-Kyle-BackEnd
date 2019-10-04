package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gamesgalore.dao.Account;
import com.revature.gamesgalore.exceptions.ResponseExceptionManager;
import com.revature.gamesgalore.repositories.AccountRepository;
import com.revature.gamesgalore.security.SecurityHandler;
import com.revature.gamesgalore.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	SecurityHandler securityHandler;

	public void login(HttpServletRequest request, HttpServletResponse response) {
		try {
			String accountUsername = request.getParameter("accountUsername");
			String accountPassword = request.getParameter("accountPassword");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountUsername, accountPassword));
			Account account = accountRepository.findByAccountUsername(accountUsername).orElseThrow(
					ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
			List<String> roles = new ArrayList<>();
			roles.add(account.getAccountRole().getRoleName());
			String jwt = securityHandler.createJWT(accountUsername, roles);
			response.addHeader("Access-Control-Expose-Headers", "Authorization");
			response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
			response.setHeader("Authorization", jwt);
		} catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
	}
}
