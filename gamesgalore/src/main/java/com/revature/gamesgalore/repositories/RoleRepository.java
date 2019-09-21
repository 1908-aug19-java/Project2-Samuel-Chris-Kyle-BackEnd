package com.revature.gamesgalore.repositories;

import java.util.List;
import java.util.Optional;

//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.revature.gamesgalore.models.Role;

//@Repository
public interface RoleRepository {
	Optional<Role> findById(Long id);
	Optional<Role> findByName(String name);
	List<Role> findAll();
	void save(Role role);
	void update(Role role);
	void deleteById(Long id);
}
