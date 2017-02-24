package ua.nure.butov.summaryTask4.repository.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.butov.summaryTask4.form.RegistrationForm;
import ua.nure.butov.summaryTask4.repository.impl.AccountRegistrationRepositoryImpl;



public class AbstractRepositoryImplTest {
	
	private static AccountRegistrationRepositoryImpl repository;
	
	@BeforeClass
	public static void beforeClass() {
			repository = new AccountRegistrationRepositoryImpl();
	}

	@Test
	public void testGetInsertArguments() {
		RegistrationForm form = new RegistrationForm("login","email","firstName","lastName","password","confirmPassword");
		Object[]expected = {"login","email","firstName","lastName","password"};
		try {
			Object[]result = repository.getInsertArguments(form);
			assertArrayEquals(expected, result);
		} catch (IllegalArgumentException e) {
			fail();
		} catch (IllegalAccessException e) {
			fail();
		}
	}
}
