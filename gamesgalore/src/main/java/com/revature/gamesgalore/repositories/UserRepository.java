package com.revature.gamesgalore.repositories;

import java.util.Collection;
import java.util.Optional;

import com.revature.gamesgalore.dao.User;


public interface UserRepository {

	Collection<User> findByFirstName(String userFirstName);
	Collection<User> findByLastName(String userLastName);
	Collection<User> findAll();
	Collection<User> findByQuery(String query);
	Optional<User> findById(Long userId);
	Optional<User> findByEmail(String userEmail);
	void save(User user);
	void update(User user);
	void deleteById(Long userId);
}
