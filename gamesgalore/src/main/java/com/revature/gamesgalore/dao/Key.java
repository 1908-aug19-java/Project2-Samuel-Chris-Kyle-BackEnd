package com.revature.gamesgalore.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.revature.gamesgalore.entitymappings.KeyMapping;

@Entity(name = KeyMapping.ENTITY_NAME)
@Table(name = KeyMapping.TABLE_NAME)
public class Key implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = KeyMapping.KEY_ID)
	private Long keyId;
	@Column(name = KeyMapping.KEY_STRING_ID)
	private String keyStringId;
	@Column(name = KeyMapping.KEY_SECRET)
	private String keySecret;
	@Column(name = KeyMapping.KEY_ORIGIN)
	private String keyOrigin;

	public Key() {
		super();
	}

	public Key(Long keyId, String keyStringId, String keySecret, String keyOrigin) {
		super();
		this.keyId = keyId;
		this.keyStringId = keyStringId;
		this.keySecret = keySecret;
		this.keyOrigin = keyOrigin;
	}

	@Override
	public String toString() {
		return "Key [keyId=" + keyId + ", keyStringId=" + keyStringId + ", keySecret=" + keySecret + ", keyOrigin="
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
		Key other = (Key) obj;
		if (keyId == null) {
			if (other.keyId != null)
				return false;
		} else if (!keyId.equals(other.keyId)){return false;}
		return true;
	}

}
