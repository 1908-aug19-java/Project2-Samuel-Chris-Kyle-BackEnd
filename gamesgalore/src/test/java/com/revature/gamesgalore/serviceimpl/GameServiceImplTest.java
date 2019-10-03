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
		//gameServiceImpl = new GameServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		Game game = new Game();
		game.setGameName("ppo");
		gameServiceImpl.isValidCreate(game);
	}

}
