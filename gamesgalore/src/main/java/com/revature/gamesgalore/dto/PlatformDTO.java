package com.revature.gamesgalore.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.revature.gamesgalore.dao.Account;

public class PlatformDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long platformId;
	private String platformName;
	private List<AccountDTO> platformAccounts;

	public PlatformDTO() {
		super();
	}

	public PlatformDTO(Long platformId, String platformName) {
		super();
		this.platformId = platformId;
		this.platformName = platformName;
	}

	public PlatformDTO(Long platformId, String platformName, List<AccountDTO> platformAccounts) {
		super();
		this.platformId = platformId;
		this.platformName = platformName;
		this.platformAccounts = platformAccounts;
	}

	@Override
	public String toString() {
		return "PlatformDTO [platformId=" + platformId + ", platformName=" + platformName + ", platformAccounts="
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

	public List<AccountDTO> getPlatformAccounts() {
		return platformAccounts;
	}

	public void setPlatformAccounts(List<Account> platformAccounts) {
		for (Account account : platformAccounts) {
			AccountDTO accountDTO = new AccountDTO();
			BeanUtils.copyProperties(account, accountDTO);
			this.platformAccounts.add(accountDTO);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((platformId == null) ? 0 : platformId.hashCode());
		result = prime * result + ((platformName == null) ? 0 : platformName.hashCode());
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
		PlatformDTO other = (PlatformDTO) obj;
		if (platformId == null) {
			if (other.platformId != null)
				return false;
		} else if (!platformId.equals(other.platformId))
			return false;
		if (platformName == null) {
			if (other.platformName != null)
				return false;
		} else if (!platformName.equals(other.platformName))
			return false;
		return true;
	}

}
