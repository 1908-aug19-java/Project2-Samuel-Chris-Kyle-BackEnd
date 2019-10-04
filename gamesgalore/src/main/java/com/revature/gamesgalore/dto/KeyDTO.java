package com.revature.gamesgalore.dto;

public class KeyDTO {

	private Long keyId;
	private String keyStringId;
	private String keySecret;
	private String keyOrigin;

	public KeyDTO() {
		super();
	}

	public KeyDTO(Long keyId, String keyStringId, String keySecret, String keyOrigin) {
		super();
		this.keyId = keyId;
		this.keyStringId = keyStringId;
		this.keySecret = keySecret;
		this.keyOrigin = keyOrigin;
	}

	@Override
	public String toString() {
		return "KeyDTO [keyId=" + keyId + ", keyStringId=" + keyStringId + ", keySecret=" + keySecret + ", keyOrigin="
				+ keyOrigin + "]";
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public String getKeyStringId() {
		return keyStringId;
	}

	public void setKeyStringId(String keyStringId) {
		this.keyStringId = keyStringId;
	}

	public String getKeySecret() {
		return keySecret;
	}

	public void setKeySecret(String keySecret) {
		this.keySecret = keySecret;
	}

	public String getKeyOrigin() {
		return keyOrigin;
	}

	public void setKeyOrigin(String keyOrigin) {
		this.keyOrigin = keyOrigin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyId == null) ? 0 : keyId.hashCode());
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
		KeyDTO other = (KeyDTO) obj;
		if (keyId == null) {
			if (other.keyId != null)
				return false;
		} else if (!keyId.equals(other.keyId)){return false;}
		return true;
	}

}
