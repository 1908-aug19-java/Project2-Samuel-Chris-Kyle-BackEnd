package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gamesgalore.dao.Game;
import com.revature.gamesgalore.entitymappings.GameMappings;
import com.revature.gamesgalore.repositories.GameRepository;
import com.revature.gamesgalore.service.AbstractMasterService;
import com.revature.gamesgalore.util.DetailsUtil;

@Transactional
@Service
public class GameServiceImpl extends AbstractMasterService<Game, GameRepository> {

	@Autowired
	GameRepository gameRepository;

	@Override
	public void overrideUpdatedFields(Game gameRetreived, Game game) {
		if (game.getGameName() != null) {
			gameRetreived.setGameName(game.getGameName());
		}
	}

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
	public void setCreatedDependencies(Game game) {
		isValidCreate(game);
	}

	@Override
	public boolean isValidCreate(Game game) {
		return true;
	}

	@Override
	public boolean isValidUpdate(Game dao, Game daoRetreived) {
		return true;
	}

}
