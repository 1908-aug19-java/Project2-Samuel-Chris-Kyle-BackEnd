package com.revature.gamesgalore.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gamesgalore.dao.Account;
import com.revature.gamesgalore.dto.AccountDTO;
import com.revature.gamesgalore.service.AccountService;

@RestController
public class AccountController {
	
	/**
	 * An object used to handle the business logic for all User objects. It's
	 * creation is handled by Spring's container.
	 */
	@Autowired
	AccountService accountService;

	/**
	 * 
	 * @param response      The HTTP response from the GET operation.
	 * @param accountUsername An optional query parameter for filtering results by
	 *                      account user name.
	 * @param accountRoleName  An optional query parameter for filtering results by
	 *                      account role name.
	 * @return A collection of Account POJO's.
	 */
	@GetMapping(value = "/accounts")
	public List<AccountDTO> getUsers(HttpServletResponse response, @RequestParam(required = false) String accountUsername,
			@RequestParam(required = false) String accountRoleName) {
		response.setStatus(200);
		List<Account> accounts = accountService.getAccountByParams(accountUsername, accountRoleName);
		List<AccountDTO> accountsDTO =  new ArrayList<>();
		for(Account account: accounts) {
			AccountDTO accountDTO = new AccountDTO();
			BeanUtils.copyProperties(account, accountDTO);
			accountsDTO.add(accountDTO);
		}
		return accountsDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the POST operation.
	 * @param usersDTO A array of objects containing a POJO representation of Accounts
	 *                 objects.
	 */
	@PostMapping(value = "/accounts")
	public void createUsers(HttpServletResponse response, @NotNull @RequestBody List<AccountDTO> accountsDTO) {
		List<Account> accounts = new ArrayList<>();
		for (AccountDTO accountDTO : accountsDTO) {
			Account account = new Account();
			account.copyPropertiesFrom(accountDTO);
			accounts.add(account);
		}
		response.setStatus(201);
		accountService.addAccounts((List<Account>)accounts);
	}

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param userId   The numeric id pertaining to a specific Account object. It must
	 *                 be passed in the url path.
	 * @return A specific User POJO
	 */
	@GetMapping(value = "/accounts/{id}")
	public AccountDTO getUser(HttpServletResponse response, @PathVariable("id") Long accountId) {
		response.setStatus(200);
		Account account =  accountService.getAccount(accountId);
		AccountDTO accountDTO = new AccountDTO();
		BeanUtils.copyProperties(account, accountDTO);
		return accountDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the PUT operation.
	 * @param userDTO     A POJO object representing a User object.
	 * @param userId   The numeric id pertaining to a specific User object. It must
	 *                 be passed in the url path.
	 */
	@PutMapping(value = "/accounts/{id}")
	public void putUser(HttpServletResponse response, @NotNull @RequestBody AccountDTO accountDTO, @PathVariable("id") Long accountId) {
		Account account = new Account();
		account.copyPropertiesFrom(accountDTO);
		response.setStatus(200);
		accountService.updateAccount(account, accountId);
	}

	/**
	 * 
	 * @param response The HTTP response from the DELETE operation.
	 * @param userId   The numeric id pertaining to a specific Account object. It must
	 *                 be passed in the url path.
	 */
	@DeleteMapping(value = "/accounts/{id}")
	public void deleteUser(HttpServletResponse response, @PathVariable("id") Long accountId) {
		response.setStatus(204);
		accountService.deleteAccount(accountId);
	}
}
