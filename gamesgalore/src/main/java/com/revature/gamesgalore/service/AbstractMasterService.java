package com.revature.gamesgalore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.revature.gamesgalore.exceptions.ResponseExceptionManager;

public abstract class AbstractMasterService<D, R extends JpaSpecificationExecutor<D> & CrudRepository<D, Long>> implements MasterService<D> {
// CrudRepository<DAO, ID>, JpaSpecificationExecutor<DAO>
	@Autowired
	R masterRepository;
	
	@Override
	public List<D> getByParams(String... args) {
		return masterRepository.findAll(getSpecification(args));
	}

	@Override
	public void add(List<D> daos) {
		try {
			for (D dao : daos) {
				if (!isValidCreate(dao)) {
					throw ResponseExceptionManager.getRSE(HttpStatus.BAD_REQUEST, ResponseExceptionManager.VALIDATION_FAILED)
							.get();
				} 
				setCreatedDependencies(dao);
				masterRepository.save(dao);
			}
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public void update(D dao, Long daoId) {
		try {
			D daoRetreived = masterRepository.findById(daoId).orElseThrow(
					ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
			if (!isValidUpdate(dao, daoRetreived)) {
				throw ResponseExceptionManager
						.getRSE(HttpStatus.BAD_REQUEST, ResponseExceptionManager.VALIDATION_FAILED).get();
			}
			overrideUpdatedFields(daoRetreived, dao);
			masterRepository.save(daoRetreived);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public D get(Long daoId) {
		try {
			return masterRepository.findById(daoId).orElseThrow(
					ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public void delete(Long daoId) {
		try {
			if (!masterRepository.findById(daoId).isPresent()) {
				throw ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND).get();
			}
			masterRepository.deleteById(daoId
					);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}
}
