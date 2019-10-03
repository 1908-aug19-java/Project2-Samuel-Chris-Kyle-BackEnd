package com.revature.gamesgalore.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.revature.gamesgalore.dao.Account;
import com.revature.gamesgalore.dao.Game;

public class WishlistDTO {

	private Long wishlistId;
	private String wishlistName;
	private Set<GameDTO> wishlistGames = new HashSet<>();
	private AccountDTO wishlistAccount;

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
		return "WishlistDTO [wishlistId=" + wishlistId + ", wishlistName=" + wishlistName + ", wishlistGames=" + wishlistGames
				+  "]";
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

	public Set<GameDTO> getWishlistGames() {
		return wishlistGames;
	}

	public void setWishlistGames(Set<Game> games) {
		System.out.println("ggaammmes");
		for (Game game : games) {
			GameDTO gameDTO = new GameDTO();
			BeanUtils.copyProperties(game, gameDTO);
			this.wishlistGames.add(gameDTO);
		}
	}

	public AccountDTO getWishlistAccount() {
		return wishlistAccount;
	}

	public void setWishlistAccount(Account wishlistAccount) {
		System.out.println("wishlist1");
		if (wishlistAccount != null) {
			AccountDTO wishlistAccountDTO = new AccountDTO();
			System.out.println("wishlist12");
			BeanUtils.copyProperties(wishlistAccount, wishlistAccountDTO, "accountPassword");
			System.out.println("wishlist13");
			this.wishlistAccount = wishlistAccountDTO;
		}
		System.out.println("wishlist14");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wishlistGames == null) ? 0 : wishlistGames.hashCode());
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
		if (wishlistGames == null) {
			if (other.wishlistGames != null) {
				return false;
			}
		} else if (!wishlistGames.equals(other.wishlistGames)) {
			return false;
		}
		return true;
	}

}
