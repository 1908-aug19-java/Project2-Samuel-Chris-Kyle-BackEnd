package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.revature.gamesgalore.dao.User;
import com.revature.gamesgalore.entitymappings.UserMappings;
import com.revature.gamesgalore.exceptions.ResponseExceptionManager;
import com.revature.gamesgalore.repositories.UserRepository;
import com.revature.gamesgalore.service.UserService;
import com.revature.gamesgalore.util.DetailsUtil;
import org.springframework.http.HttpStatus;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LogManager.getLogger();
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getUsersByParams(String userFirstName, String userLastName, String userEmail) {
		return userRepository.findAll(new Specification<User>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (userFirstName != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder
							.equal(root.get(DetailsUtil.toFieldName(UserMappings.USER_FIRST_NAME)), userFirstName)));
				}
				if (userLastName != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder
							.equal(root.get(DetailsUtil.toFieldName(UserMappings.USER_LAST_NAME)), userLastName)));
				}
				if (userEmail != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder
							.equal(root.get(DetailsUtil.toFieldName(UserMappings.USER_EMAIL)), userEmail)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public void addUsers(List<User> users) {
		try {
			for (User user : users) {
				if (!isValidUserCreate(user) || !(user.getUserEmail() != null && emailDoesNotExist(user.getUserEmail()))) {
					throw ResponseExceptionManager.getRSE(HttpStatus.BAD_REQUEST, ResponseExceptionManager.VALIDATION_FAILED).get();
				}
				userRepository.save(user);
			}
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			logger.error(e);
			throw ResponseExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public User getUser(Long userId) {
		try {
			return userRepository.findById(userId).orElseThrow(ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			logger.error(e);
			throw ResponseExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public void updateUser(User user, Long userId) {
		try {
			User userRetreived = userRepository.findById(userId)
					.orElseThrow(ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND));
			logger.info("in2");
			if (!isValidUserUpdate(user, userRetreived)) {
				 throw ResponseExceptionManager.getRSE(HttpStatus.BAD_REQUEST, ResponseExceptionManager.VALIDATION_FAILED).get();
			}
			logger.info("in3");
			setOverrides(userRetreived, user);
			userRepository.save(userRetreived);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			logger.error(e);
			throw ResponseExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public void deleteUser(Long userId) {
		try {
			if (!userRepository.findById(userId).isPresent()) {
			 throw ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, ResponseExceptionManager.NOT_FOUND).get();
			}
			logger.info(userId);
			userRepository.deleteById(userId);
		} catch (ResponseStatusException rse) {
			throw rse;
		} catch (Exception e) {
			logger.error(e);
			throw ResponseExceptionManager.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}

	@Override
	public boolean isValidUserCreate(User user) {
		boolean valid = (user.getUserFirstName() != null && isValidName(user.getUserFirstName()));
		valid &= (user.getUserLastName() != null && isValidName(user.getUserLastName()));
		valid &= (user.getUserEmail() != null && isValidEmail(user.getUserEmail()));
		valid &= emailDoesNotExist(user.getUserEmail());
		return valid;
	}

	@Override
	public boolean isValidUserUpdate(User user, User userRetreived) {
		boolean valid = true;
		if(user.getUserFirstName() != null) {
			valid &= isValidName(user.getUserFirstName());
		}
		if(user.getUserLastName() != null) {
			valid &= isValidName(user.getUserLastName());
		}
		if(user.getUserEmail() != null) {
			valid &= !user.getUserEmail().equals(userRetreived.getUserEmail()) ? emailDoesNotExist(user.getUserEmail()):Boolean.TRUE;
			valid &=  isValidEmail(user.getUserEmail());
		}
		return valid;
	}

	private boolean isValidName(String name) {
		return name.matches("[A-Za-z-']{2,20}");
	}

	private boolean isValidEmail(String email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		return email.matches(regex);
	}

	private boolean emailDoesNotExist(String email) {
		Optional<User> user = userRepository.findByUserEmail(email);
		return !user.isPresent();
	}

	@Override
	public void setOverrides(User userRetreived, User user) {
		if (user.getUserFirstName() != null) {
			userRetreived.setUserFirstName(user.getUserFirstName());
		}
		if (user.getUserLastName() != null) {
			userRetreived.setUserLastName(user.getUserLastName());
		}
		if (user.getUserEmail() != null) {
			userRetreived.setUserEmail(user.getUserEmail());
		}
		if (user.getUserAccount() != null) {
			userRetreived.setUserAccount(user.getUserAccount());
		}
	}
}
