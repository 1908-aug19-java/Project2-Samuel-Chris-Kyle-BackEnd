package com.revature.gamesgalore.dao.entitydetails;


/**
 * This class holds the mappings for the account class, to its representation in the database
 * and Account class information.
 */
public class AccountDetails {

	public static final String TABLE_NAME = "accounts";
	public static final String ENTITY_NAME = "account";
	public static final String ACCOUNT_ID = "account_id";
	public static final String ACCOUNT_USERNAME = "account_username";
	public static final String ACCOUNT_PASSWORD = "account_password";
	public static final String ACCOUNT_USER_ID = "account_user_id";
	public static final String ACCOUNT_ROLE_ID = "account_role_id";
	public static final String ACCOUNT_ROLE_FIELD = "accountRole";
	public static final String ACCOUNT_USER_FIELD = "accountUser";
	
	public static String[] getAccountColumns() {
		return new String[]{ACCOUNT_ID, ACCOUNT_USERNAME, ACCOUNT_PASSWORD, ACCOUNT_USER_ID, ACCOUNT_ROLE_ID};
	}
	
	private AccountDetails() {}
}
