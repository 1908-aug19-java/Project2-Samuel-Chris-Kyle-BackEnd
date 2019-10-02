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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.revature.gamesgalore.entitymappings.GameMappings;
import com.revature.gamesgalore.entitymappings.WishlistMappings;

@Entity(name = GameMappings.ENTITY_NAME)
@Table(name = GameMappings.TABLE_NAME)
public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = GameMappings.GAME_ID)
	private Long gameId;
	@Column(name = GameMappings.GAME_NAME)
	private String gameName;
	@ManyToMany(mappedBy = WishlistMappings.WISHLIST_GAMES_FIELD, fetch = FetchType.LAZY)
	private Set<Wishlist> gameWishlists = new HashSet<>();

	public Game() {
		super();
	}

	public Game(Long gameId, String gameName) {
		super();
		this.gameId = gameId;
		this.gameName = gameName;
	}

	public Game(Long gameId, String gameName, Set<Wishlist> wishlists) {
		super();
		this.gameId = gameId;
		this.gameName = gameName;
		this.gameWishlists = wishlists;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", gameName=" + gameName + ", wishlists=" + (gameWishlists != null ? gameWishlists : "Empty") + "]";
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Set<Wishlist> getGameWishlists() {
		return gameWishlists;
	}

	public void setGameWishlists(Set<Wishlist> gameWishlists) {
		this.gameWishlists = gameWishlists;
	}

	public void setGame(Game game) {
		if (game.gameId != null) {
			this.gameId = game.gameId;
		}
		if (game.gameName != null) {
			this.gameName = game.gameName;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
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
		Game other = (Game) obj;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId)) {
			return false;
		}
		return true;
	}

}
