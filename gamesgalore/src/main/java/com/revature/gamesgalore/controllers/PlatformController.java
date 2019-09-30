package com.revature.gamesgalore.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gamesgalore.dao.Platform;
import com.revature.gamesgalore.dto.PlatformDTO;
import com.revature.gamesgalore.service.PlatformService;

@CrossOrigin
@RestController
public class PlatformController {

	/**
	 * An object used to handle the business logic for all Platform objects. It's
	 * creation is handled by Spring's container.
	 */
	@Autowired
	PlatformService platformService;

	/**
	 * @param response The HTTP response from the GET operation.
	 * @param platformName Platform name to filter search
	 * @return A collection of Platform POJO's.
	 */
	@GetMapping(value = "/platforms")
	public List<PlatformDTO> getPlatforms(HttpServletResponse response, @RequestParam(required = false) String platformName) {
		response.setStatus(200);
		List<Platform> platforms = platformService.getPlatformsByParams(platformName);
		List<PlatformDTO> platformsDTO = new ArrayList<>();
		for (Platform platform: platforms) {
			PlatformDTO platformDTO = new PlatformDTO();
			BeanUtils.copyProperties(platform, platformDTO);
			platformsDTO.add(platformDTO);
		}
		return platformsDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the POST operation.
	 * @param platformsDTO A array of objects containing a POJO representation of Platforms
	 *                 objects.
	 */
	@PostMapping(value = "/platforms")
	public void createPlatforms(HttpServletResponse response, @NotNull @RequestBody List<PlatformDTO> platformsDTO) {
		List<Platform> platforms = new ArrayList<>();
		for (PlatformDTO platformDTO : platformsDTO) {
			Platform platform = new Platform();
			BeanUtils.copyProperties(platformDTO, platform);
			platforms.add(platform);
		}
		response.setStatus(201);
		platformService.addPlatforms(platforms);
	}

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param platformId   The numeric id pertaining to a specific Account object. It must
	 *                 be passed in the url path.
	 * @return A specific Platform POJO
	 */
	@GetMapping(value = "/platforms/{id}")
	public PlatformDTO getPlatform(HttpServletResponse response, @PathVariable("id") Long platformId) {
		response.setStatus(200);
		Platform platform =  platformService.getPlatform(platformId);
		PlatformDTO platformDTO = new PlatformDTO();
		BeanUtils.copyProperties(platform, platformDTO);
		return platformDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the PUT operation.
	 * @param platformDTO     A POJO object representing a Platform object.
	 * @param platformId   The numeric id pertaining to a specific Platform object. It must
	 *                 be passed in the url path.
	 */
	@PutMapping(value = "/platforms/{id}")
	public void putPlatform(HttpServletResponse response, @NotNull @RequestBody PlatformDTO platformDTO, @PathVariable("id") Long platformId) {
		Platform platform = new Platform();
		BeanUtils.copyProperties(platformDTO, platform);
		response.setStatus(200);
		System.out.println(platform);
		platformService.updatePlatform(platform, platformId);
	}

	/**
	 * 
	 * @param response The HTTP response from the DELETE operation.
	 * @param platformId   The numeric id pertaining to a specific Account object. It must
	 *                 be passed in the url path.
	 */
	@DeleteMapping(value = "/platforms/{id}")
	public void deletePlatform(HttpServletResponse response, @PathVariable("id") Long platformId) {
		response.setStatus(204);
		platformService.deletePlatform(platformId);
	}
}
