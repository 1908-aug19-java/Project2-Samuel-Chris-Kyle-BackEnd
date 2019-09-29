package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

import com.revature.gamesgalore.dao.Account;
import com.revature.gamesgalore.dao.Role;
import com.revature.gamesgalore.dao.User;
import com.revature.gamesgalore.entitymappings.AccountMappings;
import com.revature.gamesgalore.entitymappings.RoleMappings;
import com.revature.gamesgalore.exceptions.ResponseExceptionManager;
import com.revature.gamesgalore.repositories.AccountRepository;
import com.revature.gamesgalore.security.SecurityHandler;
import com.revature.gamesgalore.service.AccountService;
import com.revature.gamesgalore.service.RoleService;
import com.revature.gamesgalore.service.UserService;
import com.revature.gamesgalore.util.DetailsUtil;


@Transactional
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@Override
	public List<Account> getAccountByParams(String accountUsername, String accountRoleName) {
		return accountRepository.findAll(new Specification<Account>() {
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
		});
	}

	@Override
	public void addAccounts(List<Account> accounts) {
		try {
			for (Account account : accounts) {
				if (!isValidAccountCreate(account)) {
					throw ResponseExceptionManager.getRSE(HttpStatus.BAD_REQUEST, ResponseExceptionManager.VALIDATION_FAILED).get();
				} else {
					account.setAccountPassword(new SecurityHandler().hash(account.getAccountPassword()));
				}
				Role accountRole = account.getAccountRole();
				if (accountRole == null) {
					throw ResponseExceptionManager.getRSE(HttpStatus.BAD_REQUEST, ResponseExceptionManager.BODY_INVALID).get();
				}
				verifyRole(account, accountRole);

				List<User> accountUsers = new ArrayList<>();
				accountUsers.add(account.getAccountUser());
				userService.addUsers(accountUsers);

				accountRepository.save(account);
			}
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	private void verifyRole(Account account, Role accountRole) {
		Role roleRetreived = null;
		if (accountRole.getRoleId() != null) {
			roleRetreived = roleService.getRole(accountRole.getRoleId());
		} else if (accountRole.getRoleName() != null) {
			roleRetreived = (roleService.getRolesByParams(accountRole.getRoleName())).get(0);
		} else {
			throw ResponseExceptionManager.getRSE(HttpStatus.BAD_REQUEST, ResponseExceptionManager.BODY_INVALID).get();
		}
		if (roleRetreived == null) {
			throw ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND).get();
		} else {
			account.setAccountRole(roleRetreived);
		}
	}

	@Override
	public void updateAccount(Account account, Long accountId) {
		try {
			Account accountRetreived = accountRepository.findById(accountId)
					.orElseThrow(ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
			if (!isValidAccountUpdate(account, accountRetreived)) {
				throw ResponseExceptionManager.getRSE(HttpStatus.BAD_REQUEST, ResponseExceptionManager.VALIDATION_FAILED).get();
			}
			setOverrides(accountRetreived, account);
			accountRepository.save(accountRetreived);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public Account getAccount(Long accountId) {
		try {
			return accountRepository.findById(accountId)
					.orElseThrow(ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public void deleteAccount(Long accountId) {
		try {
			if (!accountRepository.findById(accountId).isPresent()) {
				throw ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND).get();
			}
			accountRepository.deleteById(accountId);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			throw ResponseExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public boolean isValidAccountCreate(Account account) {
		return usernameDoesNotExist(account.getAccountUsername()) && isValidUsername(account.getAccountUsername())
				&& isValidPassword(account.getAccountPassword());
	}

	@Override
	public boolean isValidAccountUpdate(Account account, Account accountRetreived) {
		boolean valid = !accountRetreived.getAccountUsername().equals(account.getAccountUsername())
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
	public boolean areValidCredentials(String accountUsername, String accountPassword) {
		try {
			Account account = accountRepository.findByAccountUsername(accountUsername)
					.orElseThrow(NoSuchElementException::new);
			if (!new SecurityHandler().hashMatches(accountPassword, account.getAccountPassword())) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private boolean isValidUsername(String username) {
		String regex = "^[a-z0-9_-]{3,20}$";
		return username.matches(regex);
	}

	private boolean usernameDoesNotExist(String accountUsername) {
		Optional<Account> account = accountRepository.findByAccountUsername(accountUsername);
		return !account.isPresent();
	}

	@Override
	public boolean isValidPassword(String password) {
		String regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])\\w{6,}";
		return password.matches(regex);
	}

	@Override
	public boolean isSamePassword(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}

	@Override
	public void setOverrides(Account accountRetreived, Account account) {
		if (account.getAccountUsername() != null) {
			accountRetreived.setAccountUsername(account.getAccountUsername());
		}
		if (account.getAccountPassword() != null) {
			accountRetreived.setAccountPassword(account.getAccountPassword());
		}
		if (account.getAccountRole() != null) {
			Role accountRole = account.getAccountRole();
			if (accountRole.getRoleId() != null) {
				Role roleRetreived = roleService.getRole(accountRole.getRoleId());
				accountRetreived.setAccountRole(roleRetreived);
			} else if (accountRole.getRoleName() != null) {
				List<Role> rolesRetreived = roleService.getRolesByParams(accountRole.getRoleName());
				if (!rolesRetreived.isEmpty()) {
					accountRetreived.setAccountRole(rolesRetreived.get(0));
				}
			}
		}
	}
}
