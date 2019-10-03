package com.revature.gamesgalore.serviceimpl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.revature.gamesgalore.dao.Account;

@RunWith(Parameterized.class)
public class AccountServiceImplTest {

	public AccountServiceImpl accountServiceImpl;

	public AccountServiceImplTest(AccountServiceImpl accountServiceImpl) {
		this.accountServiceImpl = accountServiceImpl;
	}

	@Parameterized.Parameters
	public static Collection<Object> instancesToTest() {
		return Arrays.asList(new Object[] { new AccountServiceImpl() });
	}

	@Test
	public void getAllEmployeesTest() {
		Account a = new Account();
		when(accountServiceImpl.isValidCreate(a)).thenReturn(true);
		assertTrue(accountServiceImpl.isValidCreate(a));
	}

}
