package com.revature.gamesgalore.service;

import java.util.Collection;

import com.revature.gamesgalore.dao.Role;

/**
 * 
 * @author sdorilas
 *
 */
public interface RoleService {

	/**
	 * 
	 * @param roleName The name of an Role object that is used to filter a collection of
	 *             Roles.
	 * @return A collection of Role objects that may have been filtered by the
	 *         parameters passed.
	 */
	Collection<Role> getRolesByParams(String roleName);

	/**
	 * 
	 * @param roles A collection of Role objects
	 */
	void addRoles(Collection<Role> roles);

	/**
	 * 
	 * @param roleId A Role object.
	 * @param id A number used to get and modify a Role object.
	 */
	void updateRole(Role role, Long roleId);

	/**
	 * 
	 * @param roleId A number used to get and modify a Role object.
	 * @return A Role object
	 */
	Role getRole(Long roleId);

	/**
	 * 
	 * @param role A Role object to be validated.
	 * @return boolean stating whether all the fields of the passed Role object
	 *         are valid.
	 */
	boolean validateRole(Role role);

	/**
	 * 
	 * @param roleId A number used to get and delete a Role object.
	 */
	void deleteRole(Long roleId);
}
