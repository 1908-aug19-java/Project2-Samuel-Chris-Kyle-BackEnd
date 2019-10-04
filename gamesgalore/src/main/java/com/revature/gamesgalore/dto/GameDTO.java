package com.revature.gamesgalore.dto;

import java.io.Serializable;

public class GameDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long gameId;
	private String gameName;
	

	public GameDTO() {
		super();
	}

	public GameDTO(Long gameId, String gameName) {
		super();
		this.gameId = gameId;
		this.gameName = gameName;
	}

	@Override
	public String toString() {
		return "GameDTO [gameId=" + gameId + ", gameName=" + gameName + "]";
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
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
		GameDTO other = (GameDTO) obj;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId)) {
			return false;
		}
		return true;
	}

}
