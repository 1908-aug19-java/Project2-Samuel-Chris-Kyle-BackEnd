package com.revature.gamesgalore.models.entitydetails;

/**
 * This class holds the mappings for the User class, to its representation in the database
 *
 */
public class UserEntityDetails {

	public static final String TABLE_NAME = "users";
	public static final String ENTITY_NAME = "user";
	public static final String USER_ID = "user_id";
	public static final String USER_FIRST_NAME = "user_first_name";
	public static final String USER_LAST_NAME = "user_last_name";
	public static final String USER_EMAIL = "user_email";
	public static final String USER_ACCOUNT_ID = "user_account_id";
		
	public static String[] getUserColumns() {
		return new String[]{USER_ID, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ACCOUNT_ID};
	}
	
	private UserEntityDetails() {}
}
