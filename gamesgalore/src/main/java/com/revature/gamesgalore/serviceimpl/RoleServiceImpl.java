package com.revature.gamesgalore.serviceimpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import javax.transaction.Transactional;

import com.revature.gamesgalore.dao.Role;
import com.revature.gamesgalore.exceptions.ExceptionManager;
import com.revature.gamesgalore.repositories.RoleRepository;
import com.revature.gamesgalore.service.RoleService;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {

	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Role> getRolesByParams(String roleName) {
		try {
			if (roleName != null) {
				return roleRepository.findByRoleName(roleName);
			}
			return roleRepository.findAll();
		} catch (Exception e) {
			logger.error(e);
			throw ExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public void addRoles(List<Role> roles) {
		try {
			for (Role r : roles) {
				if (!validateRole(r)) {
					throw ExceptionManager.getRSE(HttpStatus.BAD_REQUEST, ExceptionManager.VALIDATION_FAILED).get();
				}
				roleRepository.save(r);
			}
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			logger.error(e);
			throw ExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public Role getRole(Long roleId) {
		try {
			return roleRepository.findById(roleId).orElseThrow(ExceptionManager.getRSE(HttpStatus.NOT_FOUND, ExceptionManager.NOT_FOUND));
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			logger.error(e);
			throw ExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public void updateRole(Role role, Long roleId) {
		try {
			Role roleRetreived = roleRepository.findById(roleId).orElseThrow(ExceptionManager.getRSE(HttpStatus.NOT_FOUND, ExceptionManager.NOT_FOUND));
			setOverrides(roleRetreived,role);
			if (!validateRole(roleRetreived)) {
				throw ExceptionManager.getRSE(HttpStatus.BAD_REQUEST, ExceptionManager.VALIDATION_FAILED).get();
			}
			roleRepository.save(roleRetreived);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			logger.error(e);
			throw ExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionManager.UNEXPECTED_ERROR).get();
		}
	}
	
	@Override
	public void deleteRole(Long roleId) {
		try {
			if(!roleRepository.findById(roleId).isPresent()) {
				throw ExceptionManager.getRSE(HttpStatus.NOT_FOUND, ExceptionManager.NOT_FOUND).get();
			}
			roleRepository.deleteById(roleId);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			logger.error(e);
			throw ExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionManager.UNEXPECTED_ERROR).get();
		}
	}
	
	@Override
	public boolean validateRole(Role role) {
		return role.getRoleName() != null && !role.getRoleName().isEmpty();
	}
	
	@Override
	public void setOverrides(Role roleRetreived, Role role) {
		if(role.getRoleName() != null) {roleRetreived.setRoleName(role.getRoleName());}
	}

}
