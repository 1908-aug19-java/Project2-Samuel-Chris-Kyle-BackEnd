package com.revature.gamesgalore.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface MasterService<D> {

	/**
	 * @param args Variable number of arguments that will be passed directly to a Specification
	 * @return A collection of DAO objects that may have been filtered by the
	 *         parameters passed.
	 */
	List<D> getByParams(String... args);

	/**
	 * 
	 * @param daos A collection of DAO objects
	 */
	void add(List<D> daos);

	/**
	 * 
	 * @param dao A DAO object.
	 * @param daoId A number used to get and modify a DAO object.
	 */
	void update(D dao, Long daoId);

	/**
	 * 
	 * @param daoId A number used to get and modify a DAO object.
	 * @return A DAO object
	 */
	D get(Long daoId);

	/**
	 * 
	 * @param daoId A number used to get and delete a DAO object.
	 */
	void delete(Long daoId);
	
	/**
	 * @param retreived DAO object retrieved from the database
	 * @param current DAO object used to transfer fields to retrieved DAO object
	 */
	void overrideUpdatedFields(D retreived, D current);
	
	/**
	 * @param args Any number of arguments used to filter a search on DAO objects
	 * @return A Specification used to find all DAO objects filtered by the implementation of the specification from the variable args
	 */
	Specification<D> getSpecification(String... args);
	
	/**
	 * @param dao A DAO object that will have its dependencies managed upon creation
	 */
	void setCreatedDependencies(D dao);
	
	/**
	 * @param dao A DAO object to be validated upon creation
	 * @return condition denoting if validations passed
	 */
	boolean isValidCreate(D dao);

	/**
	 * @param dao DAO object retrieved from the database used in the validation process
	 * @param daoRetreived current DAO object used in the validation process
	 * @return condition denoting if validations passed
	 */
	boolean isValidUpdate(D dao, D daoRetreived);
}
