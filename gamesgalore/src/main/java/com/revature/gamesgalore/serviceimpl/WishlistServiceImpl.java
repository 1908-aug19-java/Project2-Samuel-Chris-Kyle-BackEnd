package com.revature.gamesgalore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

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
import com.revature.gamesgalore.dao.Wishlist;
import com.revature.gamesgalore.entitymappings.AccountMappings;
import com.revature.gamesgalore.entitymappings.WishlistMappings;
import com.revature.gamesgalore.exceptions.ResponseExceptionManager;
import com.revature.gamesgalore.repositories.WIshlistRepository;
import com.revature.gamesgalore.service.AbstractMasterService;
import com.revature.gamesgalore.service.MasterService;
import com.revature.gamesgalore.util.DetailsUtil;

@Transactional
@Service
public class WishlistServiceImpl extends AbstractMasterService<Wishlist, WIshlistRepository> {

	@Autowired
	MasterService<Account> accountService;

	@Override
	public Specification<Wishlist> getSpecification(String... args) {
		String wishlistName = args[0];
		String accountUsername = args[1];
		return new Specification<Wishlist>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Wishlist> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				//.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				List<Predicate> predicates = new ArrayList<>();
				Join<Wishlist, Account> wishlistOnAccount = root.join(WishlistMappings.WISHLIST_ACCOUNT_FIELD);
				if (wishlistName != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder
							.equal(root.get(DetailsUtil.toFieldName(WishlistMappings.WISHLIST_NAME)), wishlistName)));
				}
				if (accountUsername != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(
							wishlistOnAccount.get(DetailsUtil.toFieldName(AccountMappings.ACCOUNT_USERNAME)),
							accountUsername)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	@Override
	public void overrideUpdatedFields(Wishlist wishlistRetreived, Wishlist wishlist) {
		if (wishlist.getWishlistName() != null) {
			wishlistRetreived.setWishlistName(wishlist.getWishlistName());
		}

		setDependencies(wishlist);

		Account wishlistAccount = wishlist.getWishlistAccount();
		if (wishlistAccount != null) {
			wishlistRetreived.setWishlistAccount(wishlistAccount);
		}

	}

	@Override
	public void manageCreatedDependencies(Wishlist wishlist) {
		setDependencies(wishlist);
	}

	private void setDependencies(Wishlist wishlist) {
		Account wishlistAccount = wishlist.getWishlistAccount();
		List<Account> accountsRetreived = accountService.getByParams(wishlistAccount.getAccountUsername());
		if (!accountsRetreived.isEmpty()) {
			wishlist.setWishlistAccount(accountsRetreived.get(0));
		} else {
			throw ResponseExceptionManager.getRSE(HttpStatus.NOT_FOUND, "The account dependency does not exist").get();
		}

	}

	@Override
	public boolean isValidCreate(Wishlist wishlist) {
		return true;//isValidName(wishlist.getWishlistName());
	}

	@Override
	public boolean isValidUpdate(Wishlist wishlist, Wishlist wishlistRetreived) {
		return true;
//				wishlistRetreived.getWishlistName().equals(wishlist.getWishlistName())
//				|| isValidName(wishlist.getWishlistName());
	}

	@Override
	public void manageDeletingDependencies(Wishlist wishlist) {
		// Dependency deletion not necessary since this is the owning side
	}
	
}
