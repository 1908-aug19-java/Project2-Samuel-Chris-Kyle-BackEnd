package com.revature.gamesgalore.dao;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.revature.gamesgalore.dao.entitydetails.AccountDetails;
import com.revature.gamesgalore.dao.entitydetails.RoleDetails;
import com.revature.gamesgalore.dao.entitydetails.UserDetails;

@Entity(name = AccountDetails.ENTITY_NAME)
@Table(name = AccountDetails.TABLE_NAME)
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = AccountDetails.ACCOUNT_ID)
	private Long accountId;
	@Column(name = AccountDetails.ACCOUNT_USERNAME)
	private String accountUsername;
	@Column(name = AccountDetails.ACCOUNT_PASSWORD)
	private String accountPassword;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = AccountDetails.ACCOUNT_USER_ID, referencedColumnName = UserDetails.USER_ID, nullable = false)
	private User accountUser;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = AccountDetails.ACCOUNT_ROLE_ID, referencedColumnName = RoleDetails.ROLE_ID, nullable = false)
	private Role accountRole;

	public Account() {
		super();
	}

	public Account(Long accountId, String accountUsername, String accountPassword, User accountUser, Role accountRole) {
		super();
		this.accountId = accountId;
		this.accountUsername = accountUsername;
		this.accountPassword = accountPassword;
		this.accountUser = accountUser;
		this.accountRole = accountRole;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountUsername=" + accountUsername + ", accountPassword="
				+ accountPassword + ", accountUser=" + accountUser + ", accountRole=" + accountRole + "]";
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

	public User getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(User accountUser) {
		this.accountUser = accountUser;
	}

	public Role getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(Role accountRole) {
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
		Account other = (Account) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId)) {
			return false;
		}
		return true;
	}

}
