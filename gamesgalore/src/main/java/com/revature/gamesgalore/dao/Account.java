package com.revature.gamesgalore.dao;

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
import com.revature.gamesgalore.dao.entitydetails.UserDetails;

@Entity(name = AccountDetails.ENTITY_NAME)
@Table(name = AccountDetails.TABLE_NAME)
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = AccountDetails.ACCOUNT_ID)
	private Long accountId;
	@Column(name = AccountDetails.USERNAME)
	private String username;
	@Column(name = AccountDetails.PASSWORD)
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = AccountDetails.ACCOUNT_USER_ID, referencedColumnName = UserDetails.USER_ID, nullable = false)
	private User user;

	public Account() {
		super();
	}

	public Account(Long accountId, String username, String password, User user) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", username=" + username + ", password=" + password + ", user="
				+ user + "]";
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		} else if (!accountId.equals(other.accountId)) {return false;}
		return true;
	}

}
