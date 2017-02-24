package ua.nure.butov.summaryTask4.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.form.AddAccountForm;
import ua.nure.butov.summaryTask4.form.AuthorForm;
import ua.nure.butov.summaryTask4.form.EditAccountForm;
import ua.nure.butov.summaryTask4.form.EditBookForm;
import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.model.Author;
import ua.nure.butov.summaryTask4.model.Book;

/**
 * 
 * Provides functionality for application admins.
 * 
 * @author V.Butov
 *
 */
public interface AdminService extends Service {

	List<Account> findAllAccounts(HttpServletRequest req, int limit, int offset);

	Account findAccountById(long id);

	void checkAccountEditionData(EditAccountForm form, int minInputLenght) throws ValidationException;

	void updateAccount(EditAccountForm form);

	void checkAccountRegistrationData(AddAccountForm form) throws ValidationException;

	void addAccount(AddAccountForm form);

	List<Account> searchAccountsByLogin(HttpServletRequest req, String login, int limit, int offset);

	void deleteAccount(long id);

	List<Book> findAllBooks(HttpServletRequest req, int limit, int offset);

	Book findBookById(long id);

	List<Author> findAllAuthors();

	void updateBook(EditBookForm form);

	void checkDataAtBookForm(EditBookForm form) throws ValidationException;

	void deleteBook(long bookId);

	void createBook(EditBookForm form);

	List<Author> findAllAuthors(HttpServletRequest req, int limit, int offset);
	
	void checkDataAtAuthorForm(AuthorForm form) throws ValidationException;
	
	void createAuthor(AuthorForm form);

}
