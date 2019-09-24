package com.revature.gamesgalore.service;

import java.util.Collection;

import com.revature.gamesgalore.dao.Account;

public interface AccountService {

	/**
	 * 
	 * @param accountUsername  The user name that is used to filter a collection of Accounts
	 * @param accountRoleName The name of a role that is used to filter a collection of Accounts
	 * @return A collection of Account objects that may have been filtered by the
	 *         parameters passed.
	 */
	Collection<Account> getAccountByParams(String accountUsername, String accountRoleName);

	/**
	 * 
	 * @param accounts A collection of Account objects
	 */
	void addAccounts(Collection<Account> accounts);

	/**
	 * 
	 * @param account An Account object.
	 * @param accountId A number used to get and modify a Account object.
	 */
	void updateAccount(Account account, Long accountId);

	/**
	 * 
	 * @param accountId A number used to get and modify a Account object.
	 * @return An Account object
	 */
	Account getAccount(Long accountId);

	/**
	 * 
	 * @param user An Account object to be validated.
	 * @return boolean stating whether all the fields of the passed Account object
	 *         are valid.
	 */
	boolean isValidAccount(Account account);

	/**
	 * 
	 * @param accountId A number used to get and delete a Account object.
	 */
	void deleteAccount(Long accountId);
}
