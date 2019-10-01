package com.revature.gamesgalore.dto;

import java.io.Serializable;

public class GenreDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long genreId;
	private String genreName;

	public GenreDTO() {
		super();
	}

	public GenreDTO(Long genreId, String genreName) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
	}

	@Override
	public String toString() {
		return "GenreDTO [genreId=" + genreId + ", genreName=" + genreName + "]";
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
		} else if (!genreId.equals(other.genreId)) {
			return false;
		}
		return true;
	}

}
