package com.revature.gamesgalore.dto;

import java.util.HashSet;
import java.util.Set;

public class WishlistDTO {

	private Long wishlistId;
	private String wishlistName;
	private Set<GameDTO> games = new HashSet<>();

	public WishlistDTO() {
		super();
	}

	public WishlistDTO(Long wishlistId, String wishlistName) {
		super();
		this.wishlistId = wishlistId;
		this.wishlistName = wishlistName;
	}

	@Override
	public String toString() {
		return "WishlistDTO [wishlistId=" + wishlistId + ", wishlistName=" + wishlistName + ", games=" + games + "]";
	}

	public Long getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(Long wishlistId) {
		this.wishlistId = wishlistId;
	}

	public String getWishlistName() {
		return wishlistName;
	}

	public void setWishlistName(String wishlistName) {
		this.wishlistName = wishlistName;
	}

	public Set<GameDTO> getGames() {
		return games;
	}

	public void setGames(Set<GameDTO> games) {
		this.games = games;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((games == null) ? 0 : games.hashCode());
		result = prime * result + ((wishlistId == null) ? 0 : wishlistId.hashCode());
		result = prime * result + ((wishlistName == null) ? 0 : wishlistName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WishlistDTO other = (WishlistDTO) obj;
		if (games == null) {
			if (other.games != null)
				return false;
		} else if (!games.equals(other.games))
			return false;
		if (wishlistId == null) {
			if (other.wishlistId != null)
				return false;
		} else if (!wishlistId.equals(other.wishlistId))
			return false;
		if (wishlistName == null) {
			if (other.wishlistName != null)
				return false;
		} else if (!wishlistName.equals(other.wishlistName))
			return false;
		return true;
	}

}
