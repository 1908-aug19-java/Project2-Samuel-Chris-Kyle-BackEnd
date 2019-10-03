package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.revature.gamesgalore.dao.User;
import com.revature.gamesgalore.entitymappings.UserMappings;
import com.revature.gamesgalore.repositories.UserRepository;
import com.revature.gamesgalore.service.AbstractMasterService;
import com.revature.gamesgalore.util.DetailsUtil;

@Transactional
@Service
public class UserServiceImpl extends AbstractMasterService<User, UserRepository> {

	@Autowired
	UserRepository userRepository;

	@Override
	public Specification<User> getSpecification(String... args) {
		String userFirstName = args[0];
		String userLastName = args[1];
		String userEmail = args[2];
		return new Specification<User>() {
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
		};
	}

	@Override
	public void overrideUpdatedFields(User userRetreived, User user) {
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
	public void manageCreatedDependencies(User user) {
		isValidCreate(user);
	}

	@Override
	public boolean isValidCreate(User user) {
		boolean valid = (user.getUserFirstName() != null && isValidName(user.getUserFirstName()));
		valid &= (user.getUserLastName() != null && isValidName(user.getUserLastName()));
		valid &= (user.getUserEmail() != null && isValidEmail(user.getUserEmail()));
		valid &= emailDoesNotExist(user.getUserEmail());
		return valid;
	}

	@Override
	public boolean isValidUpdate(User user, User userRetreived) {
		boolean valid = true;
		if (user.getUserFirstName() != null) {
			valid &= isValidName(user.getUserFirstName());
		}
		if (user.getUserLastName() != null) {
			valid &= isValidName(user.getUserLastName());
		}
		if (user.getUserEmail() != null) {
			valid &= !user.getUserEmail().equals(userRetreived.getUserEmail()) ? emailDoesNotExist(user.getUserEmail())
					: Boolean.TRUE;
			valid &= isValidEmail(user.getUserEmail());
		}
		return valid;
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
	public boolean isValidName(String name) {
		return name != null && name.matches("[A-Za-z-']{2,20}");
	}
	
	@Override
	public void manageDeletingDependencies(User user) {
		// Dependency deletion not necessary since this has a one to one with account and cascades
	}
}
