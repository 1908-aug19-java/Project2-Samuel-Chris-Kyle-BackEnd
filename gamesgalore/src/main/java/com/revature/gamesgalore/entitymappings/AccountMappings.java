package com.revature.gamesgalore.entitymappings;

/**
 * This class holds the mappings for the account class, to its representation in
 * the database and Account class information.
 */
public class AccountMappings {

	public static final String TABLE_NAME = "accounts";
	public static final String ENTITY_NAME = "account";
	public static final String ACCOUNTS_GENRES = "accounts_genres";
	public static final String ACCOUNTS_PLATFORMS = "accounts_platforms";
	public static final String ACCOUNTS_GAMES = "accounts_games";
	
	public static final String ACCOUNT_ID = "account_id";
	public static final String ACCOUNT_USERNAME = "account_username";
	public static final String ACCOUNT_PASSWORD = "account_password";
	public static final String ENABLED = "enabled";
	public static final String ACCOUNT_NON_EXPIRED = "account_non_expired";
	public static final String ACCOUNT_NON_LOCKED = "account_non_locked";
	public static final String CREDENTIALS_NON_EXPIRED = "credentials_non_expired";
	public static final String ACCOUNT_IMAGEURL = "account_imageurl";
	public static final String ACCOUNT_USER_ID = "account_user_id";
	public static final String ACCOUNT_ROLE_ID = "account_role_id";

	public static final String ACCOUNT_ROLE_FIELD = "accountRole";
	public static final String ACCOUNT_USER_FIELD = "accountUser";
	public static final String GENRE_PREFERENCES_FIELD = "genrePreferences";
	public static final String PLATFORM_PREFERENCES_FIELD = "platformPreferences";
	

	public static String[] getAccountColumns() {
		return new String[] { ACCOUNT_ID, ACCOUNT_USERNAME, ACCOUNT_PASSWORD, ENABLED, ACCOUNT_NON_EXPIRED,
				ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ACCOUNT_USER_ID, ACCOUNT_ROLE_ID };
	}

	private AccountMappings() {
	}
}
