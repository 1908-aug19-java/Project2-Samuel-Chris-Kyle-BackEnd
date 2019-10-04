package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.gamesgalore.dao.Account;
import com.revature.gamesgalore.dao.Genre;
import com.revature.gamesgalore.dao.Platform;
import com.revature.gamesgalore.dao.Role;
import com.revature.gamesgalore.dao.User;
import com.revature.gamesgalore.entitymappings.AccountMappings;
import com.revature.gamesgalore.entitymappings.RoleMappings;
import com.revature.gamesgalore.exceptions.ResponseExceptionManager;
import com.revature.gamesgalore.repositories.AccountRepository;
import com.revature.gamesgalore.security.SecurityHandler;
import com.revature.gamesgalore.service.AbstractMasterService;
import com.revature.gamesgalore.service.MasterService;
import com.revature.gamesgalore.util.DetailsUtil;

@Transactional
@Service
public class AccountServiceImpl extends AbstractMasterService<Account, AccountRepository> {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	MasterService<User> userService;
	@Autowired
	MasterService<Role> roleService;
	@Autowired
	MasterService<Genre> genreService;
	@Autowired
	MasterService<Platform> platformService;

	@Override
	public Specification<Account> getSpecification(String... args) {
		String accountUsername = args[0];
		String accountRoleName = args[1];
		return new Specification<Account>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				Join<Account, Role> accountOnRole = root.join(AccountMappings.ACCOUNT_ROLE_FIELD);
				if (accountUsername != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(
							root.get(DetailsUtil.toFieldName(AccountMappings.ACCOUNT_USERNAME)), accountUsername)));
				}
				if (accountRoleName != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(
							accountOnRole.get(DetailsUtil.toFieldName(RoleMappings.ROLE_NAME)), accountRoleName)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	@Override
	public void overrideUpdatedFields(Account accountRetreived, Account account) {
		if (account.getAccountUsername() != null) {
			accountRetreived.setAccountUsername(account.getAccountUsername());
		}
		if (account.getAccountPassword() != null) {
			accountRetreived.setAccountPassword(account.getAccountPassword());
		}
		System.out.println("in override updated fields");
		if (account.getAccountImageUrl() != null) {
			System.out.println("Account Image Url: " + account.getAccountImageUrl());
			accountRetreived.setAccountImageUrl(account.getAccountImageUrl());
		}
		setDependencies(account);

		Set<Genre> genres = account.getGenrePreferences();
		if (genres != null && !genres.isEmpty()) {
			accountRetreived.setGenrePreferences(genres);
		}
		Set<Platform> platforms = account.getPlatformPreferences();
		if (platforms != null && !platforms.isEmpty()) {
			accountRetreived.setPlatformPreferences(platforms);
		}
	}

	@Override
	public void manageCreatedDependencies(Account account) {
		setDependencies(account);
		account.setAccountRole(roleService.getByParams("USER").get(0));
		List<User> accountUsers = new ArrayList<>();
		accountUsers.add(account.getAccountUser());
		userService.add(accountUsers);
	}

	@Override
	public boolean isValidCreate(Account account) {
		System.out.println("Account: ");
		System.out.println(account);
		boolean valid = usernameDoesNotExist(account.getAccountUsername())
				&& isValidUsername(account.getAccountUsername()) && isValidPassword(account.getAccountPassword());
		if (valid) {
			account.setAccountPassword(new SecurityHandler().hash(account.getAccountPassword()));
			return true;
		}
		return false;
	}

	@Override
	public boolean isValidUpdate(Account account, Account accountRetreived) {
		boolean valid = accountRetreived.getAccountUsername().equals(account.getAccountUsername())
				|| usernameDoesNotExist(account.getAccountUsername());
		if (account.getAccountUsername() != null) {
			valid &= isValidUsername(account.getAccountUsername());
		}
		if (account.getAccountPassword() != null) {
			valid &= isValidPassword(account.getAccountPassword());
		}
		return valid;
	}

	@Override
	public void manageDeletingDependencies(Account account) {
		// Dependency deletion not necessary since this is the owning side
	}

	private void setDependencies(Account account) {
		Set<Genre> genres = account.getGenrePreferences();
		for (Genre genre : genres) {
			List<Genre> retrievedGenres = genreService.getByParams(genre.getGenreName());
			if (!retrievedGenres.isEmpty()) {
				genre.setGenre(retrievedGenres.get(0));
			} else {
				throw ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, "The genre dependency does not exist")
						.get();
			}
		}

		Set<Platform> platforms = account.getPlatformPreferences();
		for (Platform platform : platforms) {
			List<Platform> retrievedPlatforms = platformService.getByParams(platform.getPlatformName());
			if (!retrievedPlatforms.isEmpty()) {
				platform.setPlatform(retrievedPlatforms.get(0));
			} else {
				throw ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, "The platform dependency does not exist")
						.get();
			}
		}
	}

	public boolean isValidUsername(String username) {
		String regex = "^[a-z0-9_-]{3,20}$";
		return username.matches(regex);
	}

	public boolean usernameDoesNotExist(String accountUsername) {
		Optional<Account> account = accountRepository.findByAccountUsername(accountUsername);
		return !account.isPresent();
	}

	public boolean isValidPassword(String password) {
		String regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])\\w{6,}";
		return password.matches(regex);
	}

}
