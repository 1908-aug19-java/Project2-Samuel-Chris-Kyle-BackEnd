package com.revature.gamesgalore.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.revature.gamesgalore.dao.Key;

public interface KeyRepository extends CrudRepository<Key, Long>, JpaSpecificationExecutor<Key> {

}
