package com.revature.gamesgalore.repositories;

import java.util.Collection;
import java.util.Optional;

import com.revature.gamesgalore.models.Role;

public interface RoleRepository {
	Optional<Role> findById(Long id);
	Optional<Role> findByName(String name);
	Collection<Role> findAll();
	Collection<Role> findByQuery(String query);
	void save(Role role);
	void update(Role role);
	void deleteById(Long id);
}
