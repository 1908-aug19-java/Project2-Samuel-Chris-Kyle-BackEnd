package com.revature.gamesgalore.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.gamesgalore.dao.Game;
import com.revature.gamesgalore.dto.GameDTO;
import com.revature.gamesgalore.service.MasterService;

public class GameControllerTest {

	@InjectMocks
	GameController gameController;
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	MasterService<Game> gameService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void getGamesExpectedTrue(){
		GameDTO gameDTO = new GameDTO();
		gameDTO.setGameId(10L);
		
		List<Game> games = new ArrayList<Game>();
		Game game = new Game();
		game.setGameId(10L);
		games.add(game);
		
		when(gameService.getByParams(anyString())).thenReturn(games);
		List<GameDTO> gameDTOs = gameController.getGames(response, anyString());
		assertEquals(gameDTO, gameDTOs.get(0));
	}
	
	@Test
	public void getGamesExpectedFalse(){
		GameDTO gameDTO = new GameDTO();
		gameDTO.setGameId(9L);
		
		List<Game> games = new ArrayList<Game>();
		Game game = new Game();
		game.setGameId(10L);
		games.add(game);
		
		when(gameService.getByParams(anyString())).thenReturn(games);
		List<GameDTO> gameDTOs = gameController.getGames(response, anyString());
		assertNotEquals(gameDTO, gameDTOs.get(0));
	}
}
