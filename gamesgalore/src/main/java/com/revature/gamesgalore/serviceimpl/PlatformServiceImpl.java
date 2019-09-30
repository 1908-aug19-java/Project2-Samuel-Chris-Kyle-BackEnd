package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.revature.gamesgalore.dao.Platform;
import com.revature.gamesgalore.entitymappings.PlatformMappings;
import com.revature.gamesgalore.exceptions.ResponseExceptionManager;
import com.revature.gamesgalore.repositories.PlatformRepository;
import com.revature.gamesgalore.util.DetailsUtil;

@Transactional
@Service
public class PlatformServiceImpl implements com.revature.gamesgalore.service.PlatformService {

	@Autowired
	PlatformRepository platformRepository;

	@Override
	public List<Platform> getPlatformsByParams(String platformName) {
		return platformRepository.findAll(new Specification<Platform>() {
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
		});
	}

	@Override
	public void addPlatforms(List<Platform> platforms) {
		for (Platform platform : platforms) {
			platformRepository.save(platform);
		}
	}

	@Override
	public void updatePlatform(Platform platform, Long platformId) {
		try {
			Platform platformRetreived = platformRepository.findById(platformId).orElseThrow(
					ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
			platformRetreived.setPlatform(platform);
			platformRepository.save(platformRetreived);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public Platform getPlatform(Long platformId) {
		try {
			return platformRepository.findById(platformId).orElseThrow(
					ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public void deletePlatform(Long platformId) {
		try {
			if (!platformRepository.findById(platformId).isPresent()) {
				throw ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND).get();
			}
			platformRepository.deleteById(platformId);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

}
