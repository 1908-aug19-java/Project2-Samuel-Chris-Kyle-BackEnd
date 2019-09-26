package com.revature.gamesgalore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.gamesgalore.dao.User;

@Service
public interface UserService {

	/**
	 * 
	 * @param userFirstName  The first name of a User object that is used to filter a collection
	 *              of Users.
	 * @param userLastName  The last name of a User object that is used to filter a collection
	 *              of Users.
	 * @param userEmail The email of a User object that is used to filter a collection
	 *              of Users.
	 * @return A collection of User objects that may have been filtered by the
	 *         parameters passed.
	 */
	List<User> getUsersByParams(String userFirstName, String userLastName, String userEmail);

	/**
	 * 
	 * @param users A collection of User objects
	 */
	void addUsers(List<User> users);

	/**
	 * 
	 * @param user A User object.
	 * @param userId A number used to get and modify a User object.
	 */
	void updateUser(User user, Long userId);

	/**
	 * 
	 * @param userId A number used to get and modify a User object.
	 * @return A User object
	 */
	User getUser(Long userId);

	/**
	 * 
	 * @param userId A number used to get and delete a User object.
	 */
	void deleteUser(Long userId);
	
	/**
	 * 
	 * @param user A User object to be validated.
	 * @return boolean stating whether all the fields of the passed User object
	 *         are valid.
	 */
	boolean isValidUserCreate(User user);
	
	
	/**
	 * @param user A User object to be validated.
	 * @param userRetreived A User object used to validate another User object.
	 * @return denotes boolean stating whether all the fields of the passed User object
	 *         are valid.
	 */
	boolean isValidUserUpdate(User user, User userRetreived);

	/**
	 * @param userRetreived User retrieved from the database
	 * @param user User used to transfer fields to retrieved User
	 */
	void setOverrides(User userRetreived, User user);

}

