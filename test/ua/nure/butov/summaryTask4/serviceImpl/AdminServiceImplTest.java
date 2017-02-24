package ua.nure.butov.summaryTask4.serviceImpl;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.repository.AccountRepository;
import ua.nure.butov.summaryTask4.repository.AuthorRepository;
import ua.nure.butov.summaryTask4.repository.BookAuthorRepository;
import ua.nure.butov.summaryTask4.repository.BookRepository;

public class AdminServiceImplTest {

	private AccountRepository accountRepository;
	private BookRepository bookRepository;
	private AuthorRepository authorRepository;
	private BookAuthorRepository bookAuthorRepository;
	private HttpServletRequest request;
	private AdminServiceImpl adminServiceImpl;
	
	@Before
	public void before(){
		accountRepository = Mockito.mock(AccountRepository.class);
		bookRepository = Mockito.mock(BookRepository.class);
		authorRepository = Mockito.mock(AuthorRepository.class);
		bookAuthorRepository = Mockito.mock(BookAuthorRepository.class);
		request = Mockito.mock(HttpServletRequest.class);
		adminServiceImpl = new AdminServiceImpl(accountRepository, bookRepository, authorRepository, bookAuthorRepository);
	}
	
	@Test
	public void testFindAllAccounts(){
		List<Account> result = Collections.emptyList();
		Mockito.when(accountRepository.findAll(10, 0)).thenReturn(result);
		Mockito.when(accountRepository.countFoundedRecords()).thenReturn(5);
		
		List<Account> list = adminServiceImpl.findAllAccounts(request, 10, 0);
		
		Mockito.verify(accountRepository).findAll(10, 0);
		Mockito.verify(accountRepository).countFoundedRecords();
		Mockito.verify(request).setAttribute("foundRecords", 5);
		
		Assert.assertSame(result, list);
	}
}
