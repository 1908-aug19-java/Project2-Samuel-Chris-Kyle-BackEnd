package com.revature.gamesgalore.entitymappings;

public class WishlistMappings {

	public static final String TABLE_NAME = "wishlists";
	public static final String ENTITY_NAME = "wishlist";
	public static final String WISHLISTS_GAMES = "wishlists_games";
	
	public static final String WISHLIST_ID = "wishlist_id";
	public static final String WISHLIST_NAME = "wishlist_name";
	public static final String WISHLIST_ACCOUNT_ID = "wishlist_account_id";
	
	public static final String ACCOUNT_ROLE_FIELD = "accountRole";
	
	public static final String WISHLIST_ACCOUNT_FIELD = "wishlistAccount";
	public static final String WISHLIST_GAMES_FIELD = "wishlistGames";

	
	public static String[] getWishlistColumns() {
		return new String[] { WISHLIST_ID, WISHLIST_NAME};
	}

	private WishlistMappings() {
	}
}
