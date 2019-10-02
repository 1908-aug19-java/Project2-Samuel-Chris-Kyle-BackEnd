package com.revature.gamesgalore.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gamesgalore.dao.Game;
import com.revature.gamesgalore.dto.GameDTO;
import com.revature.gamesgalore.service.MasterService;
@CrossOrigin
@RestController
public class GameController {

	/**
	 * An object used to handle the business logic for all Game objects. It's
	 * creation is handled by Spring's container.
	 */
	@Autowired
	MasterService<Game> gameService;
	 
	/**
	 * @param response The HTTP response from the GET operation.
	 * @param gameName The game name to filter search
	 * @return A collection of Game POJO's.
	 */
	@GetMapping(value = "/games")
	public List<GameDTO> getGames(HttpServletResponse response, @RequestParam(required = false) String gameName) {
		response.setStatus(200);
		List<Game> games = gameService.getByParams(gameName);
		List<GameDTO> gameDTOs = new ArrayList<>();
		for (Game game : games) {
			GameDTO gameDTO = new GameDTO();
			BeanUtils.copyProperties(game, gameDTO);
			gameDTOs.add(gameDTO);
		}
		return gameDTOs;
	}

	/**
	 * 
	 * @param response The HTTP response from the POST operation.
	 * @param gameDTOs A array of objects containing a POJO representation of Game
	 *                 objects.
	 */
	@PreAuthorize("hasAuthority('ADMiN')")
	@PostMapping(value = "/games")
	public void createGames(HttpServletResponse response, @NotNull @RequestBody List<GameDTO> gameDTOs) {
		List<Game> games = new ArrayList<>();
		for (GameDTO gameDTO : gameDTOs) {
			Game game = new Game();
			BeanUtils.copyProperties(gameDTO, game);
			games.add(game);
		}
		response.setStatus(201);
		gameService.add(games);
	}

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param gameId   The numeric id pertaining to a specific Account object. It
	 *                 must be passed in the url path.
	 * @return A specific Game POJO
	 */
	@GetMapping(value = "/games/{id}")
	public GameDTO getGame(HttpServletResponse response, @PathVariable("id") Long gameId) {
		response.setStatus(200);
		Game game = gameService.get(gameId);
		GameDTO gameDTO = new GameDTO();
		BeanUtils.copyProperties(game, gameDTO);
		return gameDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the PUT operation.
	 * @param gameDTO  A POJO object representing a Game object.
	 * @param gameId   The numeric id pertaining to a specific Game object. It must
	 *                 be passed in the url path.
	 */
	@PutMapping(value = "/games/{id}")
	public void putGame(HttpServletResponse response, @NotNull @RequestBody GameDTO gameDTO,
			@PathVariable("id") Long gameId) {
		Game game = new Game();
		BeanUtils.copyProperties(gameDTO, game);
		response.setStatus(200);
		gameService.update(game, gameId);
	}

	/**
	 * 
	 * @param response The HTTP response from the DELETE operation.
	 * @param gameId   The numeric id pertaining to a specific Account object. It
	 *                 must be passed in the url path.
	 */
	@DeleteMapping(value = "/games/{id}")
	public void deleteGame(HttpServletResponse response, @PathVariable("id") Long gameId) {
		response.setStatus(204);
		gameService.delete(gameId);
	}
}
