package com.revature.gamesgalore.entitymappings;

public class GenreMappings {

	public static final String TABLE_NAME = "genres";
	public static final String ENTITY_NAME = "genre";
	
	public static final String GENRE_ID = "genre_id";
	public static final String GENRE_NAME = "genre_name";
	
	public static String[] getAccountColumns() {
		return new String[] { GENRE_ID, GENRE_NAME};
	}

	private GenreMappings() {
	}
}
