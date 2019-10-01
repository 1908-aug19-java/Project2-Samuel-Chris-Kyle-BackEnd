package com.revature.gamesgalore.springimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.revature.gamesgalore.dao.Account;

@Component
public class AccountDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account account;

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getAccountId() {
		return account.getAccountId();
	}

	public Long getAccountUserId() {
		return account.getAccountUser().getUserId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		grantedAuthorityList.add(new SimpleGrantedAuthority(account.getAccountRole().getRoleName()));
		return grantedAuthorityList;
	}

	@Override
	public String getPassword() {
		return account.getAccountPassword();
	}

	@Override
	public String getUsername() {
		return account.getAccountUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return account.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return account.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return account.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return account.isEnabled();
	}
}
