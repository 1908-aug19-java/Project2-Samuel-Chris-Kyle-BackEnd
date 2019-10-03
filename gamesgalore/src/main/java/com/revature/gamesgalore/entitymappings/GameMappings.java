package com.revature.gamesgalore.entitymappings;

public class GameMappings {

	public static final String TABLE_NAME = "games";
	public static final String ENTITY_NAME = "game";
	
	public static final String GAME_ID = "game_id";
	public static final String GAME_NAME = "game_name";
	
	public static String[] getGameColumns() {
		return new String[] { GAME_ID, GAME_NAME};
	}

	private GameMappings() {
	}
}
