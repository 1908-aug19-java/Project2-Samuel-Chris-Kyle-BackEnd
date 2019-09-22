package com.revature.gamesgalore.serviceimpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

import com.revature.gamesgalore.exceptions.ExceptionManager;
import com.revature.gamesgalore.models.Role;
import com.revature.gamesgalore.models.entitydetails.RoleEntityDetails;
import com.revature.gamesgalore.repositories.RoleRepository;
import com.revature.gamesgalore.repositoriesimpl.RoleRepositoryImpl;
import com.revature.gamesgalore.service.RoleService;
import com.revature.gamesgalore.util.QueryBuilder;

public class RoleServiceImpl implements RoleService {

	private static Logger logger = LogManager.getLogger();
	
	RoleRepository roleRepository = new RoleRepositoryImpl();

	@Override
	public Collection<Role> getRolesByQuery(String roleName) {
		try {
			QueryBuilder queryBuilder = new QueryBuilder();
			queryBuilder.getSelectAll(RoleEntityDetails.TABLE_NAME);
			if (roleName != null) {
				queryBuilder.addWhereClause(RoleEntityDetails.ROLE_NAME, roleName, true);
			}
			logger.info(queryBuilder.getQuery().toString());
			return roleRepository.findByQuery( queryBuilder.getQuery().toString());
		} catch (Exception e) {
			logger.error(e);
			throw ExceptionManager.supplierThrows500Exception().get();
		}
	}

	@Override
	public void addRoles(Collection<Role> roles) {
		try {
			for (Role r : roles) {
				if (!validateRole(r)) {
					throw ExceptionManager.supplierThrows400Exception().get();
				}
				roleRepository.save(r);
			}
		} catch (ResponseStatusException rse) {
			throw ExceptionManager.supplierThrows400Exception().get();
		} catch (Exception e) {
			logger.error(e);
			throw ExceptionManager.supplierThrows500Exception().get();
		}
	}

	@Override
	public boolean validateRole(Role role) {
		return !(role.getRoleName() == null || role.getRoleName().isEmpty());
	}

	@Override
	public Role getRole(Long roleId) {
		try {
			return roleRepository.findById(roleId).orElseThrow(ExceptionManager.supplierThrows404Exception());
		} catch (ResponseStatusException rse) {
			throw ExceptionManager.supplierThrows404Exception().get();
		} catch (Exception e) {
			logger.error(e);
			throw ExceptionManager.supplierThrows500Exception().get();
		}
	}

	@Override
	public void updateRole(Role role, Long roleId) {
		try {
			Role roleRetreived = roleRepository.findById(roleId).orElseThrow(ExceptionManager.supplierThrows404Exception());
			setOverrides(roleRetreived,role);
			roleRepository.update(roleRetreived);
		} catch (ResponseStatusException rse) {
			throw ExceptionManager.supplierThrows404Exception().get();
		} catch (Exception e) {
			logger.error(e);
			throw ExceptionManager.supplierThrows500Exception().get();
		}
	}

	private void setOverrides(Role roleRetreived, Role role) {
		if(role.getRoleName() != null) {roleRetreived.setRoleName(role.getRoleName());}
	}
	
	@Override
	public void deleteRole(Long roleId) {
		try {
			roleRepository.findById(roleId).orElseThrow(ExceptionManager.supplierThrows404Exception());
			roleRepository.deleteById(roleId);
		} catch (ResponseStatusException rse) {
			throw ExceptionManager.supplierThrows404Exception().get();
		} catch (Exception e) {
			logger.error(e);
			throw ExceptionManager.supplierThrows500Exception().get();
		}
	}

}
