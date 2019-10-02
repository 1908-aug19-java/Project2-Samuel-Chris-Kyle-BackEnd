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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.gamesgalore.dto.GameDTO;
import com.revature.gamesgalore.dto.WishlistDTO;
import com.revature.gamesgalore.entitymappings.AccountMappings;
import com.revature.gamesgalore.entitymappings.GameMappings;
import com.revature.gamesgalore.entitymappings.WishlistMappings;

@Entity(name = WishlistMappings.ENTITY_NAME)
@Table(name = WishlistMappings.TABLE_NAME)
public class Wishlist implements Serializable {

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

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = WishlistMappings.WISHLIST_ACCOUNT_ID, referencedColumnName = AccountMappings.ACCOUNT_ID, nullable = false)
	private Account wishlistAccount;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = WishlistMappings.WISHLISTS_GAMES, joinColumns = @JoinColumn(name = WishlistMappings.WISHLIST_ID, referencedColumnName = WishlistMappings.WISHLIST_ID), inverseJoinColumns = @JoinColumn(name = GameMappings.GAME_ID, referencedColumnName = GameMappings.GAME_ID))
	private Set<Game> wishlistGames = new HashSet<>();

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
		return "Wishlist [wishlistId=" + wishlistId + ", wishlistName=" + wishlistName + ", wishlistGames="
				+ (wishlistGames != null ? wishlistGames : "Empty") + "]";
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

	public Account getWishlistAccount() {
		return wishlistAccount;
	}

	public void setWishlistAccount(Account wishlistAccount) {
		this.wishlistAccount = wishlistAccount;
	}

	public Set<Game> getWishlistGames() {
		return wishlistGames;
	}

	public void setWishlistGames(Set<Game> wishlistGames) {
		this.wishlistGames = wishlistGames;
	}

	public void copyPropertiesFrom(WishlistDTO wishlistDTO) {
		this.setWishlistId(wishlistDTO.getWishlistId());
		this.setWishlistName(wishlistDTO.getWishlistName());

//		AccountDTO accountDTO = wishlistDTO.getWishlistAccount();
//		if (accountDTO != null) {
//			Account accountCopied = new Account();
//			BeanUtils.copyProperties(accountDTO, accountCopied);
//			this.setWishlistAccount(accountCopied);
//		}

		Set<GameDTO> gameDTOs = wishlistDTO.getWishlistGames();
		Set<Game> gamesCopied = new HashSet<>();
		for (GameDTO gameDTO : gameDTOs) {
			Game game = new Game();
			BeanUtils.copyProperties(gameDTO, game);
			gamesCopied.add(game);
		}
		this.setWishlistGames(gamesCopied);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wishlistId == null) ? 0 : wishlistId.hashCode());
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
		if (wishlistId == null) {
			if (other.wishlistId != null) {
				return false;
			}
		} else if (!wishlistId.equals(other.wishlistId)) {
			return false;
		}
		return true;
	}

}
