package com.revature.gamesgalore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.gamesgalore.exceptions.ResponseExceptionManager;

@Service
public abstract class AbstractMasterService<D, R extends JpaSpecificationExecutor<D> & CrudRepository<D, Long>>
		implements MasterService<D> {

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
					throw ResponseExceptionManager
							.getRSE(HttpStatus.BAD_REQUEST, ResponseExceptionManager.VALIDATION_FAILED).get();
				}
				manageCreatedDependencies(dao);
				masterRepository.save(dao);
			}
		}catch (Exception e) {
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
		} catch (Exception e) {
		}
	}

	@Override
	public D get(Long daoId) {
		try {
			return masterRepository.findById(daoId).orElseThrow(
					ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public void delete(Long daoId) {
		try {
			Optional<D> optionalD = masterRepository.findById(daoId);
			if (!optionalD.isPresent()) {
				throw ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND).get();
			}
			D dao = optionalD.get();
			manageDeletingDependencies(dao);
			masterRepository.deleteById(daoId);
		} catch (Exception e) {
		}
	}

	@Override
	public boolean isValidName(String name) {
		return name != null && name.matches("[A-Za-z-' ]{2,30}");
	}
}
