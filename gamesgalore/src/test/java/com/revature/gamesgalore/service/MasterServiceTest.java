package com.revature.gamesgalore.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.revature.gamesgalore.dao.Account;
import com.revature.gamesgalore.serviceimpl.AccountServiceImpl;
import com.revature.gamesgalore.serviceimpl.GameServiceImpl;
import com.revature.gamesgalore.serviceimpl.GenreServiceImpl;

@RunWith(Parameterized.class)
public class MasterServiceTest {

	 public MasterService<Account> masterService;

	    public MasterServiceTest(MasterService<Account> masterService) {
	        this.masterService = masterService;
	    }

	    @Test
	    public final void testMyMethod_True() {
	        assertTrue(masterService.isValidName("Samuel"));
	    }
	    
	    @Test
	    public final void testMyMethod_False() {
	        assertFalse(masterService.isValidName(""));
	    }
	    
	    @Test
	    public final void testMyMethod_FalseNull() {
	        assertFalse(masterService.isValidName(null));
	    }

	@Parameterized.Parameters
    public static Collection<Object> instancesToTest() {
        return Arrays.asList(
                    new Object[]{new AccountServiceImpl()},
                    new Object[]{new GameServiceImpl()},
                    new Object[]{new GenreServiceImpl()}
        );
    }
}
