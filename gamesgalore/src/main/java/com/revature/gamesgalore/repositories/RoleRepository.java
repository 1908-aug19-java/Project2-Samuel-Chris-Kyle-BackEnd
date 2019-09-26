package com.revature.gamesgalore.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.gamesgalore.dao.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByRoleName(String roleName);
}
