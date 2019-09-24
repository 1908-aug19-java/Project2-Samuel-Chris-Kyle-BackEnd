package com.revature.gamesgalore.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.gamesgalore.dao.User;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User>{
	Optional<User> findByUserEmail(String userEmail);
}
