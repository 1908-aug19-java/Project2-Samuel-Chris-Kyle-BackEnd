package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.revature.gamesgalore.dao.Key;
import com.revature.gamesgalore.entitymappings.KeyMapping;
import com.revature.gamesgalore.repositories.KeyRepository;
import com.revature.gamesgalore.service.AbstractMasterService;
import com.revature.gamesgalore.util.DetailsUtil;

@Service
@Transactional
public class KeyServiceImpl extends AbstractMasterService<Key, KeyRepository>{

	@Override
	public Specification<Key> getSpecification(String... args) {
		String keyOrigin = args[0];
		return new Specification<Key>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Key> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (keyOrigin != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder
							.equal(root.get(DetailsUtil.toFieldName(KeyMapping.KEY_ORIGIN)), keyOrigin)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
	
	@Override
	public void overrideUpdatedFields(Key retreived, Key current) {
		//Not yet implemeted
	}

	@Override
	public void manageCreatedDependencies(Key dao) {
		//No dependencies
	}

	@Override
	public void manageDeletingDependencies(Key daoRetreived) {
		//No dependencies
	}

	@Override
	public boolean isValidCreate(Key dao) {
		return true;
	}

	@Override
	public boolean isValidUpdate(Key dao, Key daoRetreived) {
		return true;
	}

}
