package com.revature.gamesgalore.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.gamesgalore.dao.Account;

@Repository
@Transactional
public interface AccountRepository extends CrudRepository<Account, Long>, JpaSpecificationExecutor<Account> {
	void deleteByAccountId(Long accountId);
	Optional<Account> findByAccountUsername(String accountUsername);
}
