package com.revature.gamesgalore.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.revature.gamesgalore.dto.AccountDTO;
import com.revature.gamesgalore.dto.GenreDTO;
import com.revature.gamesgalore.dto.PlatformDTO;
import com.revature.gamesgalore.dto.UserDTO;
import com.revature.gamesgalore.entitymappings.AccountMappings;
import com.revature.gamesgalore.entitymappings.GenreMappings;
import com.revature.gamesgalore.entitymappings.PlatformMappings;
import com.revature.gamesgalore.entitymappings.RoleMappings;
import com.revature.gamesgalore.entitymappings.UserMappings;
import com.revature.gamesgalore.entitymappings.WishlistMappings;

@Entity(name = AccountMappings.ENTITY_NAME)
@Table(name = AccountMappings.TABLE_NAME)
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = AccountMappings.ACCOUNT_ID)
	private Long accountId;
	@Column(name = AccountMappings.ACCOUNT_USERNAME)
	private String accountUsername;
	@Column(name = AccountMappings.ACCOUNT_PASSWORD)
	private String accountPassword;
	@Column(name = AccountMappings.ENABLED)
	private boolean enabled = true;
	@Column(name = AccountMappings.ACCOUNT_NON_EXPIRED)
	private boolean accountNonExpired = true;
	@Column(name = AccountMappings.ACCOUNT_NON_LOCKED)
	private boolean accountNonLocked = true;
	@Column(name = AccountMappings.CREDENTIALS_NON_EXPIRED)
	private boolean credentialsNonExpired = true;
	@Column(name = AccountMappings.ACCOUNT_IMAGEURL)
	private String accountImageUrl;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = AccountMappings.ACCOUNT_USER_ID, referencedColumnName = UserMappings.USER_ID, nullable = false)
	private User accountUser;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = AccountMappings.ACCOUNT_ROLE_ID, referencedColumnName = RoleMappings.ROLE_ID, nullable = false)
	private Role accountRole;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = AccountMappings.ACCOUNTS_GENRES, joinColumns = @JoinColumn(name = AccountMappings.ACCOUNT_ID, referencedColumnName = AccountMappings.ACCOUNT_ID), inverseJoinColumns = @JoinColumn(name = GenreMappings.GENRE_ID, referencedColumnName = GenreMappings.GENRE_ID))
	private Set<Genre> genrePreferences;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = AccountMappings.ACCOUNTS_PLATFORMS, joinColumns = @JoinColumn(name = AccountMappings.ACCOUNT_ID, referencedColumnName = AccountMappings.ACCOUNT_ID), inverseJoinColumns = @JoinColumn(name = PlatformMappings.PLATFORM_ID, referencedColumnName = PlatformMappings.PLATFORM_ID))
	private Set<Platform> platformPreferences;
	
	@OneToMany(mappedBy = WishlistMappings.WISHLIST_ACCOUNT_FIELD)
	private Set<Wishlist> accountWishlist = new HashSet<>();

	public Account() {
		super();
	}

	public Account(Long accountId, String accountUsername, String accountPassword, User accountUser, Role accountRole) {
		super();
		this.accountId = accountId;
		this.accountUsername = accountUsername;
		this.accountPassword = accountPassword;
		this.accountUser = accountUser;
		this.accountRole = accountRole;
	}

	public Account(Long accountId, String accountUsername, String accountPassword, User accountUser, Role accountRole,
			Set<Genre> genrePreference, Set<Platform> platformPreferences) {
		super();
		this.accountId = accountId;
		this.accountUsername = accountUsername;
		this.accountPassword = accountPassword;
		this.accountUser = accountUser;
		this.accountRole = accountRole;
		this.genrePreferences = genrePreference;
		this.platformPreferences = platformPreferences;
	}

	@Override
	public String toString() {
		return "Account [ accountImageUrl=" +accountImageUrl + ", accountId=" + accountId + ", accountUsername=" + accountUsername + ", accountPassword="
				+ accountPassword + ", enabled=" + enabled + ", accountNonExpired=" + accountNonExpired
				+ ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired
				+ ", accountUser=" + accountUser + ", accountRole=" + accountRole + ", genrePreferences="
				+ genrePreferences + ", platformPreferences=" + platformPreferences + "]";
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

	public User getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(User accountUser) {
		this.accountUser = accountUser;
	}

	public Role getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(Role accountRole) {
		this.accountRole = accountRole;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Set<Genre> getGenrePreferences() {
		return genrePreferences;
	}

	public void setGenrePreferences(Set<Genre> genrePreferences) {
		this.genrePreferences = genrePreferences;
	}

	public Set<Platform> getPlatformPreferences() {
		return platformPreferences;
	}

	public void setPlatformPreferences(Set<Platform> platformPreferences) {
		this.platformPreferences = platformPreferences;
	}

	public String getAccountImageUrl() {
		return accountImageUrl;
	}

	public void setAccountImageUrl(String accountImageUrl) {
		this.accountImageUrl = accountImageUrl;
	}

	public Set<Wishlist> getAccountWishlist() {
		return accountWishlist;
	}

	public void setAccountWishlist(Set<Wishlist> accountWishlist) {
		this.accountWishlist = accountWishlist;
	}

	public void copyPropertiesFrom(AccountDTO accountDTO) {
		this.setAccountId(accountDTO.getAccountId());
		this.setAccountUsername(accountDTO.getAccountUsername());
		this.setAccountPassword(accountDTO.getAccountPassword());

		UserDTO accountUserDTO = accountDTO.getAccountUser();
		if (accountUserDTO != null) {
			User accountUserCopied = new User();
			BeanUtils.copyProperties(accountUserDTO, accountUserCopied);
			this.setAccountUser(accountUserCopied);
		}
		
		Set<GenreDTO> genrePreferencesDTO = accountDTO.getGenrePreferences();
		Set<Genre> genrePreferencesCopied = new HashSet<>();
		for (GenreDTO genreDTO : genrePreferencesDTO) {
			Genre genre = new Genre();
			BeanUtils.copyProperties(genreDTO, genre);
			genrePreferencesCopied.add(genre);
		}
		this.setGenrePreferences(genrePreferencesCopied);

		Set<PlatformDTO> platformPreferencesDTO = accountDTO.getPlatformPreferences();
		Set<Platform> platformPreferencesCopied = new HashSet<>();
		for (PlatformDTO platformDTO : platformPreferencesDTO) {
			Platform platform = new Platform();
			BeanUtils.copyProperties(platformDTO, platform);
			platformPreferencesCopied.add(platform);
		}
		this.setPlatformPreferences(platformPreferencesCopied);

	}

	public void setAccount(Account account) {
		this.accountId = account.getAccountId();
		this.accountUsername = account.getAccountUsername();
		this.accountRole = account.getAccountRole();
		this.genrePreferences = account.getGenrePreferences();
		this.platformPreferences = account.getPlatformPreferences();
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
		Account other = (Account) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId)) {
			return false;
		}
		return true;
	}

}
