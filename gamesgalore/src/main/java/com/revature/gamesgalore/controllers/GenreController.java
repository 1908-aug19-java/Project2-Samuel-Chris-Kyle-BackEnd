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

import com.revature.gamesgalore.dao.Genre;
import com.revature.gamesgalore.dto.GenreDTO;
import com.revature.gamesgalore.service.GenreService;

@RestController
public class GenreController {

	/**
	 * An object used to handle the business logic for all Genre objects. It's
	 * creation is handled by Spring's container.
	 */
	@Autowired
	GenreService genreService;

	/**
	 * 
	
	 */
	/**
	 * @param response  The HTTP response from the GET operation.
	 * @param genreName The genre name to filter search
	 * @return A collection of Genre POJO's.
	 */
	@GetMapping(value = "/genres")
	public List<GenreDTO> getGenres(HttpServletResponse response, @RequestParam(required = false) String genreName) {
		response.setStatus(200);
		List<Genre> genres = genreService.getGenresByParams(genreName);
		List<GenreDTO> genreDTOs = new ArrayList<>();
		for (Genre genre : genres) {
			GenreDTO genreDTO = new GenreDTO();
			BeanUtils.copyProperties(genre, genreDTO);
			genreDTOs.add(genreDTO);
		}
		return genreDTOs;
	}

	/**
	 * 
	 * @param response  The HTTP response from the POST operation.
	 * @param genreDTOs A array of objects containing a POJO representation of Genre
	 *                  objects.
	 */
	@PostMapping(value = "/genres")
	public void createGenres(HttpServletResponse response, @NotNull @RequestBody List<GenreDTO> genreDTOs) {
		List<Genre> genres = new ArrayList<>();
		for (GenreDTO genreDTO : genreDTOs) {
			Genre genre = new Genre();
			BeanUtils.copyProperties(genreDTO, genre);
			genres.add(genre);
		}
		response.setStatus(201);
		genreService.addGenres(genres);
	}

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param genreId  The numeric id pertaining to a specific Account object. It
	 *                 must be passed in the url path.
	 * @return A specific Genre POJO
	 */
	@GetMapping(value = "/genres/{id}")
	public GenreDTO getGenre(HttpServletResponse response, @PathVariable("id") Long genreId) {
		response.setStatus(200);
		Genre genre = genreService.getGenre(genreId);
		GenreDTO genreDTO = new GenreDTO();
		BeanUtils.copyProperties(genre, genreDTO);
		return genreDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the PUT operation.
	 * @param genreDTO A POJO object representing a Genre object.
	 * @param genreId  The numeric id pertaining to a specific Genre object. It must
	 *                 be passed in the url path.
	 */
	@PutMapping(value = "/genres/{id}")
	public void putGenre(HttpServletResponse response, @NotNull @RequestBody GenreDTO genreDTO,
			@PathVariable("id") Long genreId) {
		Genre genre = new Genre();
		BeanUtils.copyProperties(genreDTO, genre);
		response.setStatus(200);
		genreService.updateGenre(genre, genreId);
	}

	/**
	 * 
	 * @param response The HTTP response from the DELETE operation.
	 * @param genreId  The numeric id pertaining to a specific Account object. It
	 *                 must be passed in the url path.
	 */
	@DeleteMapping(value = "/genres/{id}")
	public void deleteGenre(HttpServletResponse response, @PathVariable("id") Long genreId) {
		response.setStatus(204);
		genreService.deleteGenre(genreId);
	}
}
