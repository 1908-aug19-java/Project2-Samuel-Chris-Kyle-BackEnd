package com.revature.gamesgalore.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.revature.gamesgalore.dao.Game;

public interface GameRepository extends CrudRepository<Game, Long>, JpaSpecificationExecutor<Game>{

}
