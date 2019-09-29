package com.revature.gamesgalore.dao;

import java.io.Serializable;
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
import com.revature.gamesgalore.entitymappings.GenreMappings;

@Entity(name = GenreMappings.ENTITY_NAME)
@Table(name = GenreMappings.TABLE_NAME)
public class Genre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = GenreMappings.GENRE_ID)
	private Long genreId;
	@Column(name = GenreMappings.GENRE_NAME)
	private String genreName;
	@ManyToMany(mappedBy = AccountMappings.GENRE_PREFERENCES_FIELD, fetch = FetchType.LAZY)
	private List<Account> genreAccounts;

	public Genre() {
		super();
	}

	public Genre(Long genreId, String genreName) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
	}

	public Genre(Long genreId, String genreName, List<Account> genreAccounts) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
		this.genreAccounts = genreAccounts;
	}

	@Override
	public String toString() {
		return "Genre [genreId=" + genreId + ", genreName=" + genreName + ", genreAccounts=" + genreAccounts + "]";
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
		Genre other = (Genre) obj;
		if (genreId == null) {
			if (other.genreId != null)
				return false;
		} else if (!genreId.equals(other.genreId))
			return false;
		return true;
	}

}
