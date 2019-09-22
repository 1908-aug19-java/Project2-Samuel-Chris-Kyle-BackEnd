package com.revature.gamesgalore.dao.entitydetails;


/**
 * This class holds the mappings for the account class, to its representation in the database
 *
 */
public class AccountEntityDetails {

	public static final String TABLE_NAME = "accounts";
	public static final String ENTITY_NAME = "account";
	public static final String ACCOUNT_ID = "account_id";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String ACCOUNT_USER_ID = "account_user_id";
	
	public static String[] getAccountColumns() {
		return new String[]{ACCOUNT_ID, USERNAME, PASSWORD, ACCOUNT_USER_ID};
	}
	
	private AccountEntityDetails() {}
}
