package com.revature.gamesgalore.controllers;

import java.util.ArrayList;
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

import com.revature.gamesgalore.dao.Key;
import com.revature.gamesgalore.dto.KeyDTO;
import com.revature.gamesgalore.service.MasterService;

@CrossOrigin
@RestController
public class KeyController {

	/**
	 * An object used to handle the business logic for all Key objects. It's
	 * creation is handled by Spring's container.
	 */
	@Autowired
	MasterService<Key> keyService;

	/**
	 * 
	
	 */
	/**
	 * @param response  The HTTP response from the GET operation.
	 * @param keyOrigin The key origin to filter search
	 * @return A collection of Key POJO's.
	 */
	@GetMapping(value = "/keys")
	public List<KeyDTO> getKeys(HttpServletResponse response, @RequestParam(required = false) String keyOrigin) {
		response.setStatus(200);
		List<Key> keys = keyService.getByParams(keyOrigin);
		List<KeyDTO> keyDTOs = new ArrayList<>();
		for (Key key : keys) {
			KeyDTO keyDTO = new KeyDTO();
			BeanUtils.copyProperties(key, keyDTO);
			keyDTOs.add(keyDTO);
		}
		return keyDTOs;
	}

	/**
	 * 
	 * @param response  The HTTP response from the POST operation.
	 * @param genreDTOs A array of objects containing a POJO representation of Key
	 *                  objects.
	 */
	@PostMapping(value = "/keys")
	public void createKeys(HttpServletResponse response, @NotNull @RequestBody List<KeyDTO> keyDTOs) {
		List<Key> keys = new ArrayList<>();
		for (KeyDTO keyDTO : keyDTOs) {
			Key key = new Key();
			BeanUtils.copyProperties(keyDTO, key);
			keys.add(key);
		}
		response.setStatus(201);
		keyService.add(keys);
	}

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param genreId  The numeric id pertaining to a specific Account object. It
	 *                 must be passed in the url path.
	 * @return A specific Key POJO
	 */
	@GetMapping(value = "/keys/{id}")
	public KeyDTO getGenre(HttpServletResponse response, @PathVariable("id") Long keyId) {
		response.setStatus(200);
		Key key = keyService.get(keyId);
		KeyDTO keyDTO = new KeyDTO();
		BeanUtils.copyProperties(key, keyDTO);
		return keyDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the PUT operation.
	 * @param genreDTO A POJO object representing a Key object.
	 * @param genreId  The numeric id pertaining to a specific Key object. It must
	 *                 be passed in the url path.
	 */
	@PutMapping(value = "/keys/{id}")
	public void putGenre(HttpServletResponse response, @NotNull @RequestBody KeyDTO keyDTO,
			@PathVariable("id") Long keyId) {
		Key key = new Key();
		BeanUtils.copyProperties(keyDTO, key);
		response.setStatus(200);
		keyService.update(key, keyId);
	}

	/**
	 * 
	 * @param response The HTTP response from the DELETE operation.
	 * @param genreId  The numeric id pertaining to a specific Account object. It
	 *                 must be passed in the url path.
	 */
	@DeleteMapping(value = "/keys/{id}")
	public void deleteGenre(HttpServletResponse response, @PathVariable("id") Long keyId) {
		response.setStatus(204);
		keyService.delete(keyId);
	}
}
