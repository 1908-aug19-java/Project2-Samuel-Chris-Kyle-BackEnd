package com.revature.gamesgalore.models.entitydetails;

/**
 * This class holds the mappings for the Role class, to its representation in the database
 *
 */
public class RoleEntityDetails {

	public static final String TABLE_NAME = "roles";
	public static final String ENTITY_NAME = "role";
	public static final String ROLE_ID = "role_id";
	public static final String ROLE_NAME = "role_name";
		
	public static String[] getRoleColumns() {
		return new String[]{ROLE_ID, ROLE_NAME};
	}
	
	private RoleEntityDetails() {}
}
