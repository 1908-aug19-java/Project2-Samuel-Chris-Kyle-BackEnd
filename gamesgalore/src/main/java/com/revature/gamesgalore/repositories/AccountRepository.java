package com.revature.gamesgalore.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.revature.gamesgalore.dao.Account;

public interface AccountRepository extends CrudRepository<Account, Long>, JpaSpecificationExecutor<Account>{

}
