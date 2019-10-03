package com.revature.gamesgalore.controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gamesgalore.dao.User;
import com.revature.gamesgalore.dto.UserDTO;
import com.revature.gamesgalore.service.MasterService;

@CrossOrigin
@RestController
public class UserController {

	/**
	 * An object used to handle the business logic for all User objects. It's
	 * creation is handled by Spring's container.
	 */
	@Autowired
	MasterService<User> userService;

	/**
	 * 
	 * @param response      The HTTP response from the GET operation.
	 * @param userFirstName An optional query parameter for filtering results by
	 *                      user first name.
	 * @param userLastName  An optional query parameter for filtering results by
	 *                      user last name.
	 * @param userEmail     An optional query parameter for filtering results by
	 *                      user email.
	 * @return A collection of User POJO's.
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/users")
	public Collection<UserDTO> getUsers(HttpServletResponse response, @RequestParam(required = false) String userFirstName,
			@RequestParam(required = false) String userLastName, @RequestParam(required = false) String userEmail) {
		response.setStatus(200);
		Collection<User> users = userService.getByParams(userFirstName, userLastName, userEmail);
		Collection<UserDTO> usersDTO =  new ArrayList<>();
		for(User user: users) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			usersDTO.add(userDTO);
		}
		return usersDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param userId   The numeric id pertaining to a specific User object. It must
	 *                 be passed in the url path.
	 * @return A specific User POJO
	 */
	@PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.accountUserId")
	@GetMapping(value = "/users/{id}")
	public UserDTO getUser(HttpServletResponse response, @PathVariable("id") Long userId) {
		response.setStatus(200);
		User user =  userService.get(userId);
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the PUT operation.
	 * @param userDTO     A POJO object representing a User object.
	 * @param userId   The numeric id pertaining to a specific User object. It must
	 *                 be passed in the url path.
	 */
	@PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.accountUserId")
	@PutMapping(value = "/users/{id}")
	public void putUser(HttpServletResponse response, @NotNull @RequestBody UserDTO userDTO, @PathVariable("id") Long userId) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		response.setStatus(200);
		userService.update(user, userId);
	}

	/**
	 * 
	 * @param response The HTTP response from the DELETE operation.
	 * @param userId   The numeric id pertaining to a specific User object. It must
	 *                 be passed in the url path.
	 */
	@PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.accountUserId")
	@DeleteMapping(value = "/users/{id}")
	public void deleteUser(HttpServletResponse response, @PathVariable("id") Long userId) {
		response.setStatus(204);
		userService.delete(userId);
	}
}
