package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.revature.gamesgalore.dao.User;
import com.revature.gamesgalore.dao.entitydetails.UserDetails;
import com.revature.gamesgalore.exceptions.ExceptionManager;
import com.revature.gamesgalore.repositories.UserRepository;
import com.revature.gamesgalore.service.UserService;
import com.revature.gamesgalore.util.DetailsUtil;

@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LogManager.getLogger();
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Collection<User> getUsersByParams(String userFirstName, String userLastName, String userEmail){
        return userRepository.findAll(new Specification<User>() {
			private static final long serialVersionUID = 1L;

			@Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(userFirstName!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(DetailsUtil.toFieldName(UserDetails.USER_FIRST_NAME)), userFirstName)));
                }
                if(userLastName!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(DetailsUtil.toFieldName(UserDetails.USER_LAST_NAME)), userLastName)));
                }
                if(userEmail!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(DetailsUtil.toFieldName(UserDetails.USER_EMAIL)), userEmail)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

	@Override
	public void addUsers(Collection<User> users) {
		try {
			for (User u : users) {
				if (!isValidUser(u)) {
					throw ExceptionManager.supplierThrows400Exception().get();
				}
				userRepository.save(u);
			}
		} catch (ResponseStatusException rse) {
			throw ExceptionManager.supplierThrows400Exception().get();
		} catch (Exception e) {
			logger.info(e);
			throw ExceptionManager.supplierThrows500Exception().get();
		}
	}

	@Override
	public boolean isValidUser(User user) {
		boolean valid = true;
		valid = valid && (user.getUserFirstName() != null && isValidName(user.getUserFirstName()));
		logger.info(valid);
		valid = valid && (user.getUserLastName() != null && isValidName(user.getUserLastName()));
		logger.info(valid);
		valid = valid && (user.getUserEmail() != null && isValidEmail(user.getUserEmail()));
		logger.info(valid);
		valid = valid && (user.getUserEmail() != null && emailDoesNotExist(user.getUserEmail()));
		logger.info(valid);
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
	public User getUser(Long userId) {
		try {
			return userRepository.findById(userId).orElseThrow(ExceptionManager.supplierThrows404Exception());
		} catch (ResponseStatusException rse) {
			throw ExceptionManager.supplierThrows404Exception().get();
		} catch (Exception e) {
			logger.info(e);
			throw ExceptionManager.supplierThrows500Exception().get();
		}
	}

	@Override
	public void updateUser(User user, Long userId) {
		try {
			User userRetreived = userRepository.findById(userId)
					.orElseThrow(ExceptionManager.supplierThrows404Exception());
			setOverrides(userRetreived, user);
			userRepository.save(userRetreived);
		} catch (ResponseStatusException rse) {
			throw ExceptionManager.supplierThrows404Exception().get();
		} catch (Exception e) {
			logger.info(e);
			throw ExceptionManager.supplierThrows500Exception().get();
		}
	}

	private void setOverrides(User userRetreived, User user) {
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

	@Override
	public void deleteUser(Long id) {
		try {
			userRepository.findById(id).orElseThrow(ExceptionManager.supplierThrows404Exception());
			userRepository.deleteById(id);
		} catch (ResponseStatusException rse) {
			throw ExceptionManager.supplierThrows404Exception().get();
		} catch (Exception e) {
			logger.info(e);
			throw ExceptionManager.supplierThrows500Exception().get();
		}
	}

}
