package com.revature.gamesgalore.entitymappings;

public class KeyMapping {

	public static final String TABLE_NAME = "keys";
	public static final String ENTITY_NAME = "key";
	
	public static final String KEY_ID = "key_id";
	public static final String KEY_STRING_ID = "key_string_id";
	public static final String KEY_SECRET = "key_secret";
	public static final String KEY_ORIGIN = "key_origin";
	
	public static String[] getGenreColumns() {
		return new String[] { KEY_ID, KEY_SECRET, KEY_ORIGIN};
	}

	private KeyMapping() {
	}
}
