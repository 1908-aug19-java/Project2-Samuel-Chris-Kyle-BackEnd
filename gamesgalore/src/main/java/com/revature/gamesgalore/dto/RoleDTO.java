package com.revature.gamesgalore.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long roleId;
	private String roleName;
	private AccountDTO roleAccount;

	public RoleDTO() {
		super();
	}

	public RoleDTO(Long roleId, String roleName, AccountDTO roleAccount) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleAccount = roleAccount;
	}

	@Override
	public String toString() {
		return "RoleDTO [roleId=" + roleId + ", roleName=" + roleName + ", roleAccount=" + roleAccount + "]";
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public AccountDTO getRoleAccount() {
		return roleAccount;
	}

	public void setRoleAccount(AccountDTO roleAccount) {
		this.roleAccount = roleAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		RoleDTO other = (RoleDTO) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId)) {
			return false;
		}
		return true;
	}

}
