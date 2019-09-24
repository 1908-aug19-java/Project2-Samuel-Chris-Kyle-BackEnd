package com.revature.gamesgalore.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.revature.gamesgalore.dao.entitydetails.RoleDetails;

@Entity(name = RoleDetails.ENTITY_NAME)
@Table(name = RoleDetails.TABLE_NAME)
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = RoleDetails.ROLE_ID)
	private Long roleId;

	@Column(name = RoleDetails.ROLE_NAME, nullable = false)
	@NotBlank(message = "Name is mandatory")
	private String roleName;

	@Transient
	@OneToOne(mappedBy = RoleDetails.ENTITY_NAME)
	private transient Account roleAccount;

	public Role() {
		super();
	}

	public Role(Long roleId) {
		super();
		this.roleId = roleId;
	}

	public Role(Long roleId, @NotBlank(message = "Name is mandatory") String name) {
		super();
		this.roleId = roleId;
		this.roleName = name;
	}

	public Role(Long roleId, @NotBlank(message = "Name is mandatory") String roleName, Account roleAccount) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleAccount = roleAccount;
	}

	public Role(@NotBlank(message = "Name is mandatory") String name) {
		super();
		this.roleName = name;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
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

	public void setRoleName(String name) {
		this.roleName = name;
	}

	public Account getRoleAccount() {
		return roleAccount;
	}

	public void setRoleAccount(Account roleAccount) {
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
		Role other = (Role) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId)) {
			return false;
		}
		return true;
	}

}
