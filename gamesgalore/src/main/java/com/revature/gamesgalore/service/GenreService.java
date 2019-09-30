package com.revature.gamesgalore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.gamesgalore.dao.Genre;

@Service
public interface GenreService {

	/**
	 * 
	 * @return A collection of Genre objects
	 */
	List<Genre> getGenresByParams(String genreName);

	/**
	 * 
	 * @param genres A collection of Genre objects
	 */
	void addGenres(List<Genre> genres);

	/**
	 * 
	 * @param genre A Genre object.
	 * @param genreId A number used to get and modify a Genre object.
	 */
	void updateGenre(Genre genre, Long genreId);

	/**
	 * 
	 * @param genreId A number used to get and modify a Genre object.
	 * @return An Genre object
	 */
	Genre getGenre(Long genreId);

	/**
	 * 
	 * @param genreId A number used to get and delete a Genre object.
	 */
	void deleteGenre(Long genreId);
}
