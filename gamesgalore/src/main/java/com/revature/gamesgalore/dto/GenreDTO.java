package com.revature.gamesgalore.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.revature.gamesgalore.dao.Account;

public class GenreDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long genreId;
	private String genreName;
	private List<AccountDTO> genreAccounts;

	public GenreDTO() {
		super();
	}

	public GenreDTO(Long genreId, String genreName) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
	}

	public GenreDTO(Long genreId, String genreName, List<AccountDTO> genreAccounts) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
		this.genreAccounts = genreAccounts;
	}

	@Override
	public String toString() {
		return "GenreDTO [genreId=" + genreId + ", genreName=" + genreName + ", genreAccounts=" + genreAccounts
				+ "]";
	}

	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public List<AccountDTO> getGenreAccounts() {
		return genreAccounts;
	}

	public void setGenreAccounts(List<Account> genreAccounts) {
		for (Account account : genreAccounts) {
			AccountDTO accountDTO = new AccountDTO();
			BeanUtils.copyProperties(account, accountDTO);
			this.genreAccounts.add(accountDTO);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genreId == null) ? 0 : genreId.hashCode());
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
		GenreDTO other = (GenreDTO) obj;
		if (genreId == null) {
			if (other.genreId != null)
				return false;
		} else if (!genreId.equals(other.genreId))
			return false;
		return true;
	}

}
