package com.revature.gamesgalore.entitymappings;

public class WishlistMappings {

	public static final String TABLE_NAME = "wishlists";
	public static final String ENTITY_NAME = "wishlist";
	public static final String WISHLISTS_GAMES = "wishlists_games";
	
	public static final String WISHLIST_ID = "wishlist_id";
	public static final String WISHLIST_NAME = "wishlist_name";
	public static final String GAMES_FIELD = "games";
	
	public static String[] getWishlistColumns() {
		return new String[] { WISHLIST_ID, WISHLIST_NAME};
	}

	private WishlistMappings() {
	}
}
