package com.revature.gamesgalore.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.revature.gamesgalore.entitymappings.AccountMappings;
import com.revature.gamesgalore.entitymappings.RoleMappings;

@Entity(name = RoleMappings.ENTITY_NAME)
@Table(name = RoleMappings.TABLE_NAME)
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = RoleMappings.ROLE_ID)
	private Long roleId;

	@Column(name = RoleMappings.ROLE_NAME, nullable = false)
	private String roleName;

	@OneToMany(mappedBy = AccountMappings.ACCOUNT_ROLE_FIELD)
	private List<Account> roleAccounts = new ArrayList<>();

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

	public Role(Long roleId, @NotBlank(message = "Name is mandatory") String roleName, List<Account> roleAccounts) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleAccounts = roleAccounts;
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

	public List<Account> getRoleAccounts() {
		return roleAccounts;
	}

	public void setRoleAccounts(List<Account> roleAccounts) {
		this.roleAccounts = roleAccounts;
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
