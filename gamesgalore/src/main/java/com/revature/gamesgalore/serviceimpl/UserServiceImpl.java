package com.revature.gamesgalore.serviceimpl;

import java.util.Collection;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.revature.gamesgalore.exceptions.ExceptionManager;
import com.revature.gamesgalore.models.User;
import com.revature.gamesgalore.models.entitydetails.UserEntityDetails;
import com.revature.gamesgalore.repositories.UserRepository;
import com.revature.gamesgalore.repositoriesimpl.UserRepositoryImpl;
import com.revature.gamesgalore.service.UserService;
import com.revature.gamesgalore.util.QueryBuilder;


@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LogManager.getLogger();
	UserRepository userRepository = new UserRepositoryImpl();

	@Override
	public Collection<User> getUsersByQuery(String userFirstName, String userLastName, String userEmail) {
		try {
			String [] columnparams = {userFirstName, userLastName, userEmail};
			String [] columns = UserEntityDetails.getUserColumns();
			QueryBuilder queryBuilder = new QueryBuilder();
			queryBuilder.getSelectAll(UserEntityDetails.TABLE_NAME);
			for (int i = 0; i < columnparams.length; i++) {
				if(i == 0) {
					if(columnparams[i] == null) {
						queryBuilder.addWhereClause(columns[i + 1], columns[i + 1], false);
					}else {
						queryBuilder.addWhereClause(columns[i + 1], columnparams[i], true);
					}
				}else {
					if(columnparams[i] == null) {
						queryBuilder.addAndClause(columns[i + 1], columns[i + 1], false);
					}else {
						queryBuilder.addAndClause(columns[i + 1], columnparams[i], true);
					}
				}
			}
			return userRepository.findByQuery(queryBuilder.getQuery().toString());
		} catch (Exception e) {
			logger.info(e);
			throw ExceptionManager.supplierThrows500Exception().get();
		}
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
		valid = valid && (user.getUserLastName() != null && isValidName(user.getUserLastName()));
		valid = valid && (user.getUserEmail() != null && isValidEmail(user.getUserEmail()));
		valid = valid && (user.getUserEmail() != null && emailDoesNotExist(user.getUserEmail()));
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
		Optional<User> user = userRepository.findByEmail(email);
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
			User userRetreived = userRepository.findById(userId).orElseThrow(ExceptionManager.supplierThrows404Exception());
			setOverrides(userRetreived,user);
			userRepository.update(userRetreived);
		} catch (ResponseStatusException rse) {
			throw ExceptionManager.supplierThrows404Exception().get();
		} catch (Exception e) {
			logger.info(e);
			throw ExceptionManager.supplierThrows500Exception().get();
		}
	}

	private void setOverrides(User userRetreived, User user) {
		if(user.getUserFirstName() != null) {userRetreived.setUserFirstName(user.getUserFirstName());}
		if(user.getUserLastName() != null) {userRetreived.setUserLastName(user.getUserLastName());}
		if(user.getUserEmail() != null) {userRetreived.setUserEmail(user.getUserEmail());}
		if(user.getUserAccount() != null) {userRetreived.setUserAccount(user.getUserAccount());}
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
