package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gamesgalore.dao.Platform;
import com.revature.gamesgalore.entitymappings.PlatformMappings;
import com.revature.gamesgalore.repositories.PlatformRepository;
import com.revature.gamesgalore.service.AbstractMasterService;
import com.revature.gamesgalore.util.DetailsUtil;

@Transactional
@Service
public class PlatformServiceImpl extends AbstractMasterService<Platform, PlatformRepository> {

	@Autowired
	PlatformRepository platformRepository;

	@Override
	public Specification<Platform> getSpecification(String... args) {
		String platformName = args[0];
		return new Specification<Platform>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Platform> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (platformName != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder
							.equal(root.get(DetailsUtil.toFieldName(PlatformMappings.PLATFORM_NAME)), platformName)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	@Override
	public void overrideUpdatedFields(Platform platformRetreived, Platform platform) {
		if (platform.getPlatformName() != null) {
			platformRetreived.setPlatformName(platform.getPlatformName());
		}
	}

	@Override
	public void manageCreatedDependencies(Platform platform) {
		// Platform has no control over dependencies so this method will not be
		// implemented.
	}

	@Override
	public boolean isValidCreate(Platform platform) {
		return isValidName(platform.getPlatformName());
	}

	@Override
	public boolean isValidUpdate(Platform platform, Platform platformRetreived) {
		return platformRetreived.getPlatformName().equals(platform.getPlatformName())
				|| isValidName(platform.getPlatformName());
	}

}
