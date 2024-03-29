package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gamesgalore.dao.Game;
import com.revature.gamesgalore.dao.Wishlist;
import com.revature.gamesgalore.entitymappings.GameMappings;
import com.revature.gamesgalore.repositories.GameRepository;
import com.revature.gamesgalore.service.AbstractMasterService;
import com.revature.gamesgalore.service.MasterService;
import com.revature.gamesgalore.util.DetailsUtil;

@Transactional
@Service
public class GameServiceImpl extends AbstractMasterService<Game, GameRepository> {

	@Autowired
	GameRepository gameRepository;
	
	@Autowired
	MasterService<Wishlist> wishlistService;

	@Override
	public Specification<Game> getSpecification(String... args) {
		String gameName = args[0];
		return new Specification<Game>() {
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
		};
	}

	@Override
	public void overrideUpdatedFields(Game gameRetreived, Game game) {
		if (game.getGameName() != null) {
			gameRetreived.setGameName(game.getGameName());
		}
	}

	@Override
	public void manageCreatedDependencies(Game game) {
		// Game has no control over dependencies so this method will not be implemented.
	}

	@Override
	public boolean isValidCreate(Game game) {
		return isValidName(game.getGameName());
	}

	@Override
	public boolean isValidUpdate(Game game, Game gameRetreived) {
		return gameRetreived.getGameName().equals(game.getGameName()) || isValidName(game.getGameName());
	}
	
	@Override
	public void manageDeletingDependencies(Game gameRetreived) {
		List<Wishlist> wishlists = wishlistService.getByParams(null, null, gameRetreived.getGameName());
		for(Wishlist wishlist: wishlists) {
			Set<Game> games = wishlist.getWishlistGames();
			for(Game game: games) {
				if(game.getGameName().equals(gameRetreived.getGameName())) {
					games.remove(game);
				}
			}
		}
	}

}
