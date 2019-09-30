package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.revature.gamesgalore.dao.Genre;
import com.revature.gamesgalore.entitymappings.GenreMappings;
import com.revature.gamesgalore.exceptions.ResponseExceptionManager;
import com.revature.gamesgalore.repositories.GenreRepository;
import com.revature.gamesgalore.service.GenreService;
import com.revature.gamesgalore.util.DetailsUtil;

@Transactional
@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	GenreRepository genreRepository;

	@Override
	public List<Genre> getGenresByParams(String genreName) {
		return genreRepository.findAll(new Specification<Genre>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Genre> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (genreName != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder
							.equal(root.get(DetailsUtil.toFieldName(GenreMappings.GENRE_NAME)), genreName)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public void addGenres(List<Genre> genres) {
		for (Genre genre : genres) {
			genreRepository.save(genre);
		}
	}

	@Override
	public void updateGenre(Genre genre, Long genreId) {
		try {
			Genre genreRetreived = genreRepository.findById(genreId).orElseThrow(
					ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
			genreRetreived.setGenre(genre);
			genreRepository.save(genreRetreived);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public Genre getGenre(Long genreId) {
		try {
			return genreRepository.findById(genreId).orElseThrow(
					ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public void deleteGenre(Long genreId) {
		try {
			if (!genreRepository.findById(genreId).isPresent()) {
				throw ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND).get();
			}
			genreRepository.deleteById(genreId);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager
					.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

}
