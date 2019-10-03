package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gamesgalore.dao.Account;
import com.revature.gamesgalore.dao.Genre;
import com.revature.gamesgalore.entitymappings.GenreMappings;
import com.revature.gamesgalore.repositories.GenreRepository;
import com.revature.gamesgalore.service.AbstractMasterService;
import com.revature.gamesgalore.service.MasterService;
import com.revature.gamesgalore.util.DetailsUtil;

@Transactional
@Service
public class GenreServiceImpl extends AbstractMasterService<Genre, GenreRepository> {

	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	MasterService<Account> accountService;

	@Override
	public Specification<Genre> getSpecification(String... args) {
		String genreName = args[0];
		return new Specification<Genre>() {
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
		};
	}

	@Override
	public void overrideUpdatedFields(Genre genreRetreived, Genre genre) {
		if (genre.getGenreName() != null) {
			genreRetreived.setGenreName(genre.getGenreName());
		}
	}

	@Override
	public void manageCreatedDependencies(Genre genre) {
		// Genre has no control over dependencies so this method will not be
		// implemented.
	}

	@Override
	public boolean isValidCreate(Genre genre) {
		return isValidName(genre.getGenreName());
	}

	@Override
	public boolean isValidUpdate(Genre genre, Genre genreRetreived) {
		return genreRetreived.getGenreName().equals(genre.getGenreName()) || isValidName(genre.getGenreName());
	}
	
	@Override
	public void manageDeletingDependencies(Genre genreReteived) {
		List<Account> accounts = accountService.getByParams("", "");
		for(Account account: accounts) {
			Set<Genre> genres = account.getGenrePreferences();
			for(Genre genre: genres) {
				if(genre.getGenreName().equals(genreReteived.getGenreName())) {
					genres.remove(genre);
				}
			}
		}
	}

}
