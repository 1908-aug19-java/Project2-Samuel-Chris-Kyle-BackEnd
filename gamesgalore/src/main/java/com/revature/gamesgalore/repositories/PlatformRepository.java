package com.revature.gamesgalore.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.revature.gamesgalore.dao.Platform;

public interface PlatformRepository extends CrudRepository<Platform, Long>, JpaSpecificationExecutor<Platform>  {

}
