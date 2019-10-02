package com.revature.gamesgalore.entitymappings;

public class PlatformMappings {

	public static final String TABLE_NAME = "platforms";
	public static final String ENTITY_NAME = "platform";
	
	public static final String PLATFORM_ID = "platform_id";
	public static final String PLATFORM_NAME = "platform_name";
	
	public static String[] getPlatformColumns() {
		return new String[] { PLATFORM_ID, PLATFORM_NAME};
	}

	private PlatformMappings() {
	}
}
