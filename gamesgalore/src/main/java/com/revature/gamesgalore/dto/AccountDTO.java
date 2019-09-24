package com.revature.gamesgalore.dto;

import java.io.Serializable;

public class AccountDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accountId;
	private String accountUsername;
	private String accountPassword;
	private String confirmPassword;
	private UserDTO accountUser;
	private RoleDTO accountRole;

	public AccountDTO() {
		super();
	}

	public AccountDTO(Long accountId, String accountUsername, String accountPassword, String confirmPassword,
			UserDTO accountUser, RoleDTO accountRole) {
		super();
		this.accountId = accountId;
		this.accountUsername = accountUsername;
		this.accountPassword = accountPassword;
		this.confirmPassword = confirmPassword;
		this.accountUser = accountUser;
		this.accountRole = accountRole;
	}

	@Override
	public String toString() {
		return "AccountDTO [accountId=" + accountId + ", accountUsername=" + accountUsername + ", accountPassword="
				+ accountPassword + ", confirmPassword=" + confirmPassword + ", accountUser=" + accountUser
				+ ", accountRole=" + accountRole + "]";
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountUsername() {
		return accountUsername;
	}

	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public UserDTO getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(UserDTO accountUser) {
		this.accountUser = accountUser;
	}

	public RoleDTO getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(RoleDTO accountRole) {
		this.accountRole = accountRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountDTO other = (AccountDTO) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId)) {
			return false;
		}
		return true;
	}

}
