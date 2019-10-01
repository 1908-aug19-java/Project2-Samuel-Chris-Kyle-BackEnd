package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.revature.gamesgalore.dao.Game;
import com.revature.gamesgalore.entitymappings.GameMappings;
import com.revature.gamesgalore.exceptions.ResponseExceptionManager;
import com.revature.gamesgalore.repositories.GameRepository;
import com.revature.gamesgalore.service.GameService;
import com.revature.gamesgalore.util.DetailsUtil;

@Transactional
@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameRepository gameRepository;

	@Override
	public List<Game> getGamesByParams(String gameName) {
		return gameRepository.findAll(new Specification<Game>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Game> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (gameName != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder
							.equal(root.get(DetailsUtil.toFieldName(GameMappings.GAME_NAME)), gameName)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public void addGames(List<Game> games) {
		for (Game game : games) {
			gameRepository.save(game);
		}
	}

	@Override
	public void updateGame(Game game, Long gameId) {
		try {
			Game gameRetreived = gameRepository.findById(gameId).orElseThrow(
					ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
			gameRetreived.setGame(game);
			gameRepository.save(gameRetreived);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public Game getGame(Long gameId) {
		try {
			return gameRepository.findById(gameId).orElseThrow(
					ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public void deleteGame(Long gameId) {
		try {
			if (!gameRepository.findById(gameId).isPresent()) {
				throw ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND).get();
			}
			gameRepository.deleteById(gameId);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

}
