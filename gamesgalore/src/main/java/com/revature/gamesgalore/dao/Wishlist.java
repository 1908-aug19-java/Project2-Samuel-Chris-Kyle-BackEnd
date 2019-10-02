package com.revature.gamesgalore.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.revature.gamesgalore.entitymappings.GameMappings;
import com.revature.gamesgalore.entitymappings.WishlistMappings;

@Entity(name = WishlistMappings.ENTITY_NAME)
@Table(name = WishlistMappings.TABLE_NAME)
public class Wishlist implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = WishlistMappings.WISHLIST_ID)
	private Long wishlistId;
	@Column(name = WishlistMappings.WISHLIST_NAME)
	private String wishlistName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = WishlistMappings.WISHLISTS_GAMES, joinColumns = @JoinColumn(name = WishlistMappings.WISHLIST_ID, referencedColumnName = WishlistMappings.WISHLIST_ID), inverseJoinColumns = @JoinColumn(name = GameMappings.GAME_ID, referencedColumnName = GameMappings.GAME_ID))
	private Set<Game> games = new HashSet<>();

	public Wishlist() {
		super();
	}

	public Wishlist(Long wishlistId, String wishlistName) {
		super();
		this.wishlistId = wishlistId;
		this.wishlistName = wishlistName;
	}

	@Override
	public String toString() {
		return "Wishlist [wishlistId=" + wishlistId + ", wishlistName=" + wishlistName + ", games=" + games + "]";
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

	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
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
		Wishlist other = (Wishlist) obj;
		if (games == null) {
			if (other.games != null)
				return false;
		} else if (!games.equals(other.games))
			return false;
		if (wishlistId == null) {
			if (other.wishlistId != null)
				return false;
		} else if (!wishlistId.equals(other.wishlistId)) {
			return false;
		}
		if (wishlistName == null) {
			if (other.wishlistName != null) {
				return false;
			}
		} else if (!wishlistName.equals(other.wishlistName)) {
			return false;
		}
		return true;
	}

}
