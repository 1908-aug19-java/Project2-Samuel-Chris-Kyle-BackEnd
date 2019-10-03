package com.revature.gamesgalore.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.revature.gamesgalore.dao.Genre;
import com.revature.gamesgalore.dao.Platform;
import com.revature.gamesgalore.dao.User;
import com.revature.gamesgalore.dao.Wishlist;

public class AccountDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accountId;
	private String accountUsername;
	private String accountPassword;
	private String confirmPassword;
	private UserDTO accountUser;
	private String accountImageUrl;
	private Set<GenreDTO> genrePreferences = new HashSet<>();
	private Set<PlatformDTO> platformPreferences = new HashSet<>();
	private Set<WishlistDTO> accountWishlist = new HashSet<>();

	public AccountDTO() {
		super();
	}

	public AccountDTO(Long accountId, String accountUsername, String accountPassword, String confirmPassword,
			UserDTO accountUser) {
		super();
		this.accountId = accountId;
		this.accountUsername = accountUsername;
		this.accountPassword = accountPassword;
		this.confirmPassword = confirmPassword;
		this.accountUser = accountUser;
	}

	public AccountDTO(Long accountId, String accountUsername, String accountPassword, String confirmPassword,
			UserDTO accountUser, String accountImageUrl, Set<GenreDTO> genrePreferences,
			Set<PlatformDTO> platformPreferences, Set<WishlistDTO> accountWishlist) {
		super();
		this.accountId = accountId;
		this.accountUsername = accountUsername;
		this.accountPassword = accountPassword;
		this.confirmPassword = confirmPassword;
		this.accountUser = accountUser;
		this.accountImageUrl = accountImageUrl;
		this.genrePreferences = genrePreferences;
		this.platformPreferences = platformPreferences;
		this.accountWishlist = accountWishlist;
	}

	@Override
	public String toString() {
		return "AccountDTO [accountId=" + accountId + ", accountUsername=" + accountUsername + ", accountPassword="
				+ accountPassword + ", confirmPassword=" + confirmPassword + ", accountUser=" + accountUser
				+ ", genrePreferences=" + genrePreferences + ", platformPreferences=" + platformPreferences + "]";
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountUsername() {
		return accountUsername;
	}

	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public UserDTO getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(User accountUser) {
		UserDTO accountUserDTO = new UserDTO();
		BeanUtils.copyProperties(accountUser, accountUserDTO);
		this.accountUser = accountUserDTO;
	}

	public Set<GenreDTO> getGenrePreferences() {
		return genrePreferences;
	}

	public void setGenrePreferences(Set<Genre> genrePreferences) {
		for (Genre genre : genrePreferences) {
			GenreDTO genreDTO = new GenreDTO();
			BeanUtils.copyProperties(genre, genreDTO);
			this.genrePreferences.add(genreDTO);
		}
	}

	public Set<PlatformDTO> getPlatformPreferences() {
		return platformPreferences;
	}

	public void setPlatformPreferences(Set<Platform> platformPreferences) {
		for (Platform platform : platformPreferences) {
			PlatformDTO platformDTO = new PlatformDTO();
			BeanUtils.copyProperties(platform, platformDTO);
			this.platformPreferences.add(platformDTO);
		}
	}

	public String getAccountImageUrl() {
		return accountImageUrl;
	}

	public void setAccountImageUrl(String accountImageUrl) {
		this.accountImageUrl = accountImageUrl;
	}

	public Set<WishlistDTO> getAccountWishlist() {
		return accountWishlist;
	}

	public void setAccountWishlist(Set<Wishlist> accountWishlist) {
		for (Wishlist wishlist : accountWishlist) {
			WishlistDTO wishlistDTO = new WishlistDTO();
			BeanUtils.copyProperties(wishlist, wishlistDTO);
			this.accountWishlist.add(wishlistDTO);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
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
		AccountDTO other = (AccountDTO) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId)) {
			return false;
		}
		return true;
	}

}
