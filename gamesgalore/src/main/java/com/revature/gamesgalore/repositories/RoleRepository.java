package com.revature.gamesgalore.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.gamesgalore.dao.Role;

@Repository
@Transactional
public interface RoleRepository extends CrudRepository<Role, Long>, JpaSpecificationExecutor<Role> {
	List<Role> findByRoleName(String roleName);
}
