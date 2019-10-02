package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.revature.gamesgalore.dao.Role;
import com.revature.gamesgalore.entitymappings.RoleMappings;
import com.revature.gamesgalore.repositories.RoleRepository;
import com.revature.gamesgalore.service.AbstractMasterService;
import com.revature.gamesgalore.util.DetailsUtil;

@Transactional
@Service
public class RoleServiceImpl extends AbstractMasterService<Role, RoleRepository> {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Specification<Role> getSpecification(String... args) {
		String roleName = args[0];
		return new Specification<Role>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (roleName != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder
							.equal(root.get(DetailsUtil.toFieldName(RoleMappings.ROLE_NAME)), roleName)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	@Override
	public void overrideUpdatedFields(Role roleRetreived, Role role) {
		if (role.getRoleName() != null) {
			roleRetreived.setRoleName(role.getRoleName());
		}
	}

	@Override
	public void manageCreatedDependencies(Role role) {
		// Role has no control over dependencies so this method will not be implemented.
	}

	@Override
	public boolean isValidCreate(Role role) {
		return isValidName(role.getRoleName());
	}

	@Override
	public boolean isValidUpdate(Role role, Role roleRetreived) {
		return roleRetreived.getRoleName().equals(role.getRoleName()) || isValidName(role.getRoleName());
	}

}
