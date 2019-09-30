package com.revature.gamesgalore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.gamesgalore.dao.Game;

@Service
public interface GameService {

	/**
	 * 
	 * @return A collection of Game objects
	 */
	List<Game> getGamesByParams(String gameName);

	/**
	 * 
	 * @param games A collection of Game objects
	 */
	void addGames(List<Game> games);

	/**
	 * 
	 * @param game A Game object.
	 * @param gameId A number used to get and modify a Game object.
	 */
	void updateGame(Game game, Long gameId);

	/**
	 * 
	 * @param gameId A number used to get and modify a Game object.
	 * @return An Game object
	 */
	Game getGame(Long gameId);

	/**
	 * 
	 * @param gameId A number used to get and delete a Game object.
	 */
	void deleteGame(Long gameId);
}
