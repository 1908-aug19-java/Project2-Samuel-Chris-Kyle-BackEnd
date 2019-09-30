package com.revature.gamesgalore.dto;

import java.io.Serializable;
import java.util.List;

public class PlatformDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long platformId;
	private String platformName;

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
	}

	@Override
	public String toString() {
		return "PlatformDTO [platformId=" + platformId + ", platformName=" + platformName + "]";
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
		PlatformDTO other = (PlatformDTO) obj;
		if (platformId == null) {
			if (other.platformId != null)
				return false;
		} else if (!platformId.equals(other.platformId)){return false;}
		return true;
	}

}
