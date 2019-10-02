package com.revature.gamesgalore.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.revature.gamesgalore.service.MasterService;
@CrossOrigin
@RestController
public class AccountController {
	
	/**
	 * An object used to handle the business logic for all Account objects. It's
	 * creation is handled by Spring's container.
	 */
	@Autowired
	MasterService<Account> accountService;

	/**
	 * 
	 * @param response      The HTTP response from the GET operation.
	 * @param accountUsername An optional query parameter for filtering results by
	 *                      account user name.
	 * @param accountRoleName  An optional query parameter for filtering results by
	 *                      account role name.
	 * @return A collection of Account POJO's.
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/accounts")
	public List<AccountDTO> getAccounts(HttpServletResponse response, @RequestParam(required = false) String accountUsername,
			@RequestParam(required = false) String accountRoleName) {
		response.setStatus(200);
		List<Account> accounts = accountService.getByParams(accountUsername, accountRoleName);
		List<AccountDTO> accountsDTO =  new ArrayList<>();
		for(Account account: accounts) {
			AccountDTO accountDTO = new AccountDTO();
			BeanUtils.copyProperties(account, accountDTO, "accountPassword");
			accountsDTO.add(accountDTO);
		}
		return accountsDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the POST operation.
	 * @param accountsDTO A array of objects containing a POJO representation of Accounts
	 *                 objects.
	 */
	@PostMapping(value = "/accounts")
	public void createAccounts(HttpServletResponse response, @NotNull @RequestBody List<AccountDTO> accountsDTO) {
		List<Account> accounts = new ArrayList<>();
		for (AccountDTO accountDTO : accountsDTO) {
			Account account = new Account();
			account.copyPropertiesFrom(accountDTO);
			accounts.add(account);
		}
		response.setStatus(201);
		accountService.add(accounts);
	}

	/**
	 * 
	 * @param response The HTTP response from the GET operation.
	 * @param accountId   The numeric id pertaining to a specific Account object. It must
	 *                 be passed in the url path.
	 * @return A specific Account POJO
	 */
	@PreAuthorize("hasAuthority('ADMIN') or #accountId == authentication.principal.accountId")
	@GetMapping(value = "/accounts/{id}")
	public AccountDTO getAccount(HttpServletResponse response, @PathVariable("id") Long accountId) {
		response.setStatus(200);
		Account account =  accountService.get(accountId);
		AccountDTO accountDTO = new AccountDTO();
		BeanUtils.copyProperties(account, accountDTO, "accountPassword");
		return accountDTO;
	}

	/**
	 * 
	 * @param response The HTTP response from the PUT operation.
	 * @param accountDTO     A POJO object representing a Account object.
	 * @param accountId   The numeric id pertaining to a specific Account object. It must
	 *                 be passed in the url path.
	 */
	@PreAuthorize("hasAuthority('ADMIN') or #accountId == authentication.principal.accountId")
	@PutMapping(value = "/accounts/{id}")
	public void putAccount(HttpServletResponse response, @NotNull @RequestBody AccountDTO accountDTO, @PathVariable("id") Long accountId) {
		Account account = new Account();
		account.copyPropertiesFrom(accountDTO);
		response.setStatus(200);
		accountService.update(account, accountId);
	}

	/**
	 * 
	 * @param response The HTTP response from the DELETE operation.
	 * @param accountId   The numeric id pertaining to a specific Account object. It must
	 *                 be passed in the url path.
	 */
	@PreAuthorize("hasAuthority('ADMIN') or #accountId == authentication.principal.accountId")
	@DeleteMapping(value = "/accounts/{id}")
	public void deleteAccount(HttpServletResponse response, @PathVariable("id") Long accountId) {
		response.setStatus(204);
		accountService.delete(accountId);
	}
}
