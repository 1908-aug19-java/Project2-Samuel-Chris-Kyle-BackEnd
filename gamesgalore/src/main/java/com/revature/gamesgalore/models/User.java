package com.revature.gamesgalore.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.revature.gamesgalore.models.entitydetails.UserEntityDetails;

@Entity(name = UserEntityDetails.ENTITY_NAME)
@Table(name = UserEntityDetails.TABLE_NAME)
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= UserEntityDetails.USER_ID)
	private Long userId;
	@Column(name= UserEntityDetails.USER_FIRST_NAME)
	private String userFirstName;
	@Column(name= UserEntityDetails.USER_LAST_NAME)
	private String userLastName;
	@Column( name= UserEntityDetails.USER_EMAIL)
	private String userEmail;
	
	@OneToOne(mappedBy = "user")
	private Account userAccount;

	public User() {
	}
	
	public User(Long userId) {
		this.userId = userId;
	}

	public User(Long userId, String userFirstName, String userLastName, String userEmail, Account userAccount) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userAccount = userAccount;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", userEmail=" + userEmail + ", userAccount=" + userAccount + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Account getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Account userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId)){return false;}
		return true;
	}
	
}
