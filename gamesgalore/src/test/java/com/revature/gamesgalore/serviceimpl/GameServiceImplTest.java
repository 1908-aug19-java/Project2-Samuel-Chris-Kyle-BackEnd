package com.revature.gamesgalore.serviceimpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.gamesgalore.dao.Game;

public class GameServiceImplTest {

	@Mock
	GameServiceImpl gameServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void validCreateTrue() {
		Game game = new Game();
		game.setGameName("myGame");
		gameServiceImpl.isValidCreate(game);
	}

}
