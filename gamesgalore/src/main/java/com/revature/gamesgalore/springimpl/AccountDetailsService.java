package com.revature.gamesgalore.springimpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revature.gamesgalore.dao.Account;
import com.revature.gamesgalore.repositories.AccountRepository;
import com.revature.gamesgalore.security.LoginAttemptService;

@Service
public class AccountDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AccountDetails accountDetails;
	@Autowired
	private LoginAttemptService loginAttemptService;
	@Autowired
	private HttpServletRequest request;

	@Override
	public AccountDetails loadUserByUsername(String accountUsername) {
		try {
			String ip = request.getRemoteAddr();
			Account account = accountRepository.findByAccountUsername(accountUsername)
					.<UsernameNotFoundException>orElseThrow(() -> {
						throw new UsernameNotFoundException(accountUsername);
					});
			Boolean blocked = loginAttemptService.isBlocked(ip);
			setLocked(account, blocked);
			accountDetails.setAccount(account);
		} catch (Exception e) {
		}
		return accountDetails;
	}

	private void setLocked(Account account, Boolean blocked) {
		account.setAccountNonLocked(!blocked);
		accountRepository.save(account);
	}

}
