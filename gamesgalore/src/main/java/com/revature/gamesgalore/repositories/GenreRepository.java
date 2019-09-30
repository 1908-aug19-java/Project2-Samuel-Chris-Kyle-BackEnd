package com.revature.gamesgalore.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.revature.gamesgalore.dao.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {

}
