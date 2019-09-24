package com.revature.gamesgalore.service;

import java.util.Collection;

import com.revature.gamesgalore.dao.User;

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
	Collection<User> getUsersByParams(String userFirstName, String userLastName, String userEmail);

	/**
	 * 
	 * @param users A collection of User objects
	 */
	void addUsers(Collection<User> users);

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
	 * @param user A User object to be validated.
	 * @return boolean stating whether all the fields of the passed User object
	 *         are valid.
	 */
	boolean isValidUser(User user);

	/**
	 * 
	 * @param userId A number used to get and delete a User object.
	 */
	void deleteUser(Long userId);

}

