package com.revature.gamesgalore.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.revature.gamesgalore.entitymappings.AccountMappings;
import com.revature.gamesgalore.entitymappings.PlatformMappings;

@Entity(name = PlatformMappings.ENTITY_NAME)
@Table(name = PlatformMappings.TABLE_NAME)
public class Platform implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = PlatformMappings.PLATFORM_ID)
	private Long platformId;
	@Column(name = PlatformMappings.PLATFORM_NAME)
	private String platformName;
	@ManyToMany(mappedBy = AccountMappings.PLATFORM_PREFERENCES_FIELD, fetch = FetchType.LAZY)
	private List<Account> platformAccounts = new ArrayList<>();

	public Platform() {
		super();
	}

	public Platform(Long platformId, String platformName) {
		super();
		this.platformId = platformId;
		this.platformName = platformName;
	}

	public Platform(Long platformId, String platformName, List<Account> platformAccounts) {
		super();
		this.platformId = platformId;
		this.platformName = platformName;
		this.platformAccounts = platformAccounts;
	}

	@Override
	public String toString() {
		return "Platform [platformId=" + platformId + ", platformName=" + platformName + ", platformAccounts="
				+ platformAccounts + "]";
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public List<Account> getPlatformAccounts() {
		return platformAccounts;
	}

	public void setPlatformAccounts(List<Account> platformAccounts) {
		this.platformAccounts = platformAccounts;
	}

	public void setPlatform(Platform platform) {
		if (platform.platformId != null) {
			this.platformId = platform.platformId;
		}
		if (platform.platformName != null) {
			this.platformName = platform.platformName;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((platformId == null) ? 0 : platformId.hashCode());
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
		Platform other = (Platform) obj;
		if (platformId == null) {
			if (other.platformId != null)
				return false;
		} else if (!platformId.equals(other.platformId)) {
			return false;
		}
		return true;
	}

}
