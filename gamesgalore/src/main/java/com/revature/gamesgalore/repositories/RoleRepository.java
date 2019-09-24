package com.revature.gamesgalore.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.gamesgalore.dao.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Collection<Role> findByRoleName(String roleName);
}
