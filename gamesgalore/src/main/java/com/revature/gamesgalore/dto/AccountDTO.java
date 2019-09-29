package com.revature.gamesgalore.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.revature.gamesgalore.dao.Genre;
import com.revature.gamesgalore.dao.Platform;
import com.revature.gamesgalore.dao.Role;
import com.revature.gamesgalore.dao.User;

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
	private RoleDTO accountRole;
	private List<GenreDTO> genrePreferences;
	private List<PlatformDTO> platformPreferences;

	public AccountDTO() {
		super();
	}

	public AccountDTO(Long accountId, String accountUsername, String accountPassword, String confirmPassword,
			UserDTO accountUser, RoleDTO accountRole) {
		super();
		this.accountId = accountId;
		this.accountUsername = accountUsername;
		this.accountPassword = accountPassword;
		this.confirmPassword = confirmPassword;
		this.accountUser = accountUser;
		this.accountRole = accountRole;
	}

	public AccountDTO(Long accountId, String accountUsername, String accountPassword, String confirmPassword,
			UserDTO accountUser, RoleDTO accountRole, List<GenreDTO> genrePreferences,
			List<PlatformDTO> platformPreferences) {
		super();
		this.accountId = accountId;
		this.accountUsername = accountUsername;
		this.accountPassword = accountPassword;
		this.confirmPassword = confirmPassword;
		this.accountUser = accountUser;
		this.accountRole = accountRole;
		this.genrePreferences = genrePreferences;
		this.platformPreferences = platformPreferences;
	}

	@Override
	public String toString() {
		return "AccountDTO [accountId=" + accountId + ", accountUsername=" + accountUsername + ", accountPassword="
				+ accountPassword + ", confirmPassword=" + confirmPassword + ", accountUser=" + accountUser
				+ ", accountRole=" + accountRole + ", genrePreferences=" + genrePreferences
				+ ", platformPreferences=" + platformPreferences + "]";
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

	public RoleDTO getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(Role accountRole) {
		RoleDTO accountRoleDTO = new RoleDTO();
		BeanUtils.copyProperties(accountRole, accountRoleDTO);
		this.accountRole = accountRoleDTO;
	}

	public List<GenreDTO> getGenrePreferences() {
		return genrePreferences;
	}

	public void setGenrePreferences(List<Genre> genrePreferences) {
		for (Genre genre : genrePreferences) {
			GenreDTO genreDTO = new GenreDTO();
			BeanUtils.copyProperties(genre, genreDTO);
			this.genrePreferences.add(genreDTO);
		}
	}

	public List<PlatformDTO> getPlatformPreferences() {
		return platformPreferences;
	}

	public void setPlatformPreferences(List<Platform> platformPreferences) {
		for (Platform platform : platformPreferences) {
			PlatformDTO platformDTO = new PlatformDTO();
			BeanUtils.copyProperties(platform, platformDTO);
			this.platformPreferences.add(platformDTO);
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
