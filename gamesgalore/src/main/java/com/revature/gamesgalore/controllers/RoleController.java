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

import com.revature.gamesgalore.models.Role;
import com.revature.gamesgalore.service.RoleService;
import com.revature.gamesgalore.serviceimpl.RoleServiceImpl;


@RestController
public class RoleController {

	/**
	 * An object used to handle the business logic for all Role objects. It's creation is handled by Spring's container.
	 */
	RoleService roleService = new RoleServiceImpl();

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param name An optional query parameter for filtering results by role name.
	 * @return A collection of Role objects.
	 */
	@GetMapping(value = "/roles")
	public Collection<Role> getRoles(HttpServletResponse response, @RequestParam(required = false) String name) {
		response.setStatus(200);
		return roleService.getRolesByQuery(name);
	}

	/**
	 * 
	 * @param response The HTTP response from the POST operation.
	 * @param roles An array of objects containing a representation of Role objects.
	 */
	@PostMapping(value = "/roles")
	public void createRoles(HttpServletResponse response, @RequestBody List<Role> roles) {
		response.setStatus(201);
		roleService.addRoles(roles);
	}

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param id The numeric id pertaining to a specific Role object. It must be passed in the url path.
	 * @return A specific Role object
	 */
	@GetMapping(value = "/roles/{id}")
	public Role getRole(HttpServletResponse response, @PathVariable Long id) {
		response.setStatus(200);
		return roleService.getRole(id);
	}

	/**
	 * 
	 * @param response The HTTP response from the PUT operation.
	 * @param role An object representing a Role object.
	 * @param id The numeric id pertaining to a specific Role object. It must be passed in the url path.
	 */
	@PutMapping(value = "/roles/{id}")
	public void putRole(HttpServletResponse response, @RequestBody Role role, @PathVariable Long id) {
		response.setStatus(200);
		roleService.updateRole(role, id);
	}

	/**
	 * 
	 * @param response The HTTP response from the DELETE operation.
	 * @param id The numeric id pertaining to a specific Role object. It must be passed in the url path.
	 */
	@DeleteMapping(value = "/roles/{id}")
	public void deleteRole(HttpServletResponse response, @PathVariable Long id) {
		response.setStatus(204);
		roleService.deleteRole(id);
	}
}
