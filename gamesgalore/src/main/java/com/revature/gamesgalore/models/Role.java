package com.revature.gamesgalore.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.revature.gamesgalore.models.entitydetails.RoleEntityDetails;

@Entity(name=RoleEntityDetails.ENTITY_NAME)
@Table(name=RoleEntityDetails.TABLE_NAME)
public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=RoleEntityDetails.ROLE_ID)
	private Long roleId;
	
	@Column(name=RoleEntityDetails.ROLE_NAME, nullable=false)
	@NotBlank(message = "Name is mandatory")
	private String roleName;

	
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


	public Role(@NotBlank(message = "Name is mandatory") String name) {
		super();
		this.roleName = name;
	}


	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + roleName + "]";
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
		} else if (!roleId.equals(other.roleId)){return false;}
		return true;
	}

}
