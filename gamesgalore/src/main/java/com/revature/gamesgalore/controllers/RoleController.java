package com.revature.gamesgalore.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gamesgalore.dao.Role;
import com.revature.gamesgalore.dto.RoleDTO;
import com.revature.gamesgalore.service.RoleService;


@RestController
public class RoleController {

	/**
	 * An object used to handle the business logic for all Role objects. It's creation is handled by Spring's container.
	 */
	@Autowired
	RoleService roleService;

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param roleName An optional query parameter for filtering results by role name.
	 * @return A collection of Role POJO's.
	 */
	@GetMapping(value = "/roles")
	public Collection<RoleDTO> getRoles(HttpServletResponse response, @RequestParam(required = false) String roleName) {
		response.setStatus(200);
		Collection<Role> roles = roleService.getRolesByParams(roleName);
		Collection<RoleDTO> rolesDTO = new ArrayList<>();
		for(Role role: roles) {
			RoleDTO roleDTO = new RoleDTO();
			BeanUtils.copyProperties(role, roleDTO);
			rolesDTO.add(roleDTO);
			
		}
		return rolesDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the POST operation.
	 * @param rolesDTO An array of objects containing POJO's of Role objects.
	 */
	@PostMapping(value = "/roles")
	public void createRoles(HttpServletResponse response, @NotNull @RequestBody List<RoleDTO> rolesDTO) {
		List<Role> roles = new ArrayList<>();
		for(RoleDTO roleDTO:rolesDTO) {
			Role role = new Role();
			BeanUtils.copyProperties(roleDTO, role);
			roles.add(role);
		}
		response.setStatus(201);
		roleService.addRoles(roles);
	}

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param roleId The numeric id pertaining to a specific Role object. It must be passed in the url path.
	 * @return A specific Role POJO
	 */
	@GetMapping(value = "/roles/{id}")
	public RoleDTO getRole(HttpServletResponse response, @PathVariable("id") Long roleId) {
		response.setStatus(200);
		Role role = roleService.getRole(roleId);
		RoleDTO roleDTO = new RoleDTO();
		BeanUtils.copyProperties(role, roleDTO);
		return roleDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the PUT operation.
	 * @param roleDTO A POJO object representing a Role object.
	 * @param roleId The numeric id pertaining to a specific Role object. It must be passed in the url path.
	 */
	@PutMapping(value = "/roles/{id}")
	public void putRole(HttpServletResponse response, @NotNull @RequestBody RoleDTO roleDTO, @PathVariable("id") Long roleId) {
		Role role = new Role();
		BeanUtils.copyProperties(roleDTO, role);
		response.setStatus(200);
		roleService.updateRole(role, roleId);
	}

	/**
	 * 
	 * @param response The HTTP response from the DELETE operation.
	 * @param roleId The numeric id pertaining to a specific Role object. It must be passed in the url path.
	 */
	@DeleteMapping(value = "/roles/{id}")
	public void deleteRole(HttpServletResponse response, @PathVariable("id") Long roleId) {
		response.setStatus(204);
		roleService.deleteRole(roleId);
	}
}
