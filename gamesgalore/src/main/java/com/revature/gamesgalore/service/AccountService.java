package com.revature.gamesgalore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.gamesgalore.dao.Account;

@Service
public interface AccountService {

	/**
	 * 
	 * @param accountUsername  The user name that is used to filter a collection of Accounts
	 * @param accountRoleName The name of a role that is used to filter a collection of Accounts
	 * @return A collection of Account objects that may have been filtered by the
	 *         parameters passed.
	 */
	List<Account> getAccountByParams(String accountUsername, String accountRoleName);

	/**
	 * 
	 * @param accounts A collection of Account objects
	 */
	void addAccounts(List<Account> accounts);

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
	 * @param accountId A number used to get and delete a Account object.
	 */
	void deleteAccount(Long accountId);
	
	/**
	 * 
	 * @param user An Account object to be validated.
	 * @return boolean stating whether all the fields of the passed Account object
	 *         are valid.
	 */
	boolean isValidAccountCreate(Account account);

	/**
	 * @param account An Account object to be validated.
	 * @param accountRetreived
	 * @return boolean stating whether all the fields of the passed Account object
	 *         are valid.
	 */
	boolean isValidAccountUpdate(Account account, Account accountRetreived);
	
	/**
	 * @param accountUsername The username associated with an account
	 * @param accountPassword The password associated with an account
	 * @return boolean stating whether all the fields of the passed Account object
	 *         are valid.
	 */
	boolean areValidCredentials(String accountUsername, String accountPassword);
	
	/**
	 * @param accountRetreived Account retrieved from the database
	 * @param account Account used to transfer fields to retrieved account
	 */
	void setOverrides(Account accountRetreived, Account account);
	
	/**
	 * @param password validates a string password against a password policy
	 * @return
	 */
	boolean isValidPassword(String password);
	
	/**
	 * @param password validates that two passwords are the same
	 * @param confirmPassword validates that two passwords are the same
	 * @return
	 */
	boolean isSamePassword(String password, String confirmPassword);
}
