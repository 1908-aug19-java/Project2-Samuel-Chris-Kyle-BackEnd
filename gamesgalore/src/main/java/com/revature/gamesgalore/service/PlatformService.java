package com.revature.gamesgalore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.gamesgalore.dao.Platform;

@Service
public interface PlatformService {

	/**
	 * 
	 * @return A collection of Platform objects
	 */
	List<Platform> getPlatformsByParams(String platformName);

	/**
	 * 
	 * @param platforms A collection of Platform objects
	 */
	void addPlatforms(List<Platform> platforms);

	/**
	 * 
	 * @param platform A Platform object.
	 * @param platformId A number used to get and modify a Platform object.
	 */
	void updatePlatform(Platform platform, Long platformId);

	/**
	 * 
	 * @param platformId A number used to get and modify a Platform object.
	 * @return An Platform object
	 */
	Platform getPlatform(Long platformId);

	/**
	 * 
	 * @param platformId A number used to get and delete a Platform object.
	 */
	void deletePlatform(Long platformId);
}
