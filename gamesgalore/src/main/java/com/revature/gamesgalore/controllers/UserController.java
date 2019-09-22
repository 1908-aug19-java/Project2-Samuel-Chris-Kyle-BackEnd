package com.revature.gamesgalore.controllers;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gamesgalore.models.User;
import com.revature.gamesgalore.service.UserService;
import com.revature.gamesgalore.serviceimpl.UserServiceImpl;

@RestController
public class UserController {

	/**
	 * An object used to handle the business logic for all User objects. It's creation is handled by Spring's container.
	 */
	UserService userService = new UserServiceImpl();

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param userFirstName An optional query parameter for filtering results by user first name.
	 * @param userLastName An optional query parameter for filtering results by user last name.
	 * @param userEmail An optional query parameter for filtering results by user email.
	 * @return A collection of User objects.
	 */
	@GetMapping(value = "/users")
	public Collection<User> getUsers(HttpServletResponse response, @RequestParam(required = false) String userFirstName,
			@RequestParam(required = false) String userLastName, @RequestParam(required = false) String userEmail) {
		response.setStatus(200);
		return userService.getUsersByQuery(userFirstName, userLastName, userEmail);
	}

	/**
	 * 
	 * @param response The HTTP response from the POST operation.
	 * @param users An array of objects containing a representation of User objects.
	 */
	@PostMapping(value = "/users")
	public void createUsers(HttpServletResponse response, @RequestBody List<User> users) {
		response.setStatus(201);
		userService.addUsers(users);
	}

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param userId The numeric id pertaining to a specific User object. It must be passed in the url path.
	 * @return A specific User object
	 */
	@GetMapping(value = "/users/{id}")
	public User getUser(HttpServletResponse response, @PathVariable("id") Long userId) {
		response.setStatus(200);
		return userService.getUser(userId);
	}
	
	/**
	 * 
	 * @param response The HTTP response from the PUT operation.
	 * @param user An object representing a User object.
	 * @param userId The numeric id pertaining to a specific User object. It must be passed in the url path.
	 */
	@PutMapping(value = "/users/{id}")
	public void putUser(HttpServletResponse response, @RequestBody User user, @PathVariable("id") Long userId) {
		response.setStatus(200);
		userService.updateUser(user, userId);
	}

	/**
	 * 
	 * @param response The HTTP response from the DELETE operation.
	 * @param userId The numeric id pertaining to a specific User object. It must be passed in the url path.
	 */
	@DeleteMapping(value = "/users/{id}")
	public void deleteUser(HttpServletResponse response, @PathVariable("id") Long userId) {
		response.setStatus(204);
		userService.deleteUser(userId);
	}
}
