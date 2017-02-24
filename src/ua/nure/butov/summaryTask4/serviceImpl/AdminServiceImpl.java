package ua.nure.butov.summaryTask4.serviceImpl;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.annotation.Transactional;
import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.form.AddAccountForm;
import ua.nure.butov.summaryTask4.form.AuthorForm;
import ua.nure.butov.summaryTask4.form.EditAccountForm;
import ua.nure.butov.summaryTask4.form.EditBookForm;
import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.model.Author;
import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.model.BookAuthor;
import ua.nure.butov.summaryTask4.model.Id;
import ua.nure.butov.summaryTask4.repository.AccountRepository;
import ua.nure.butov.summaryTask4.repository.AuthorRepository;
import ua.nure.butov.summaryTask4.repository.BookAuthorRepository;
import ua.nure.butov.summaryTask4.repository.BookRepository;
import ua.nure.butov.summaryTask4.service.AdminService;

/**
 * Provides implementation of {@link AdminService} interface.
 */
class AdminServiceImpl implements AdminService {
	private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);
	private final AccountRepository accountRepository;
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final BookAuthorRepository bookAuthorRepository;

	/**
	 * Constructor with access to all necessary repositories.
	 */
	AdminServiceImpl(AccountRepository accountRepository, BookRepository bookRepository,
			AuthorRepository authorRepository, BookAuthorRepository bookAuthorRepository) {
		this.accountRepository = accountRepository;
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.bookAuthorRepository = bookAuthorRepository;
	}

	@Transactional
	@Override
	public List<Account> findAllAccounts(HttpServletRequest req, int limit, int offset) {
		List<Account> result = accountRepository.findAll(limit, offset);
		int foundRecords = accountRepository.countFoundedRecords();
		req.setAttribute("foundRecords", foundRecords);
		return result;
	}

	@Transactional
	@Override
	public Account findAccountById(final long id) {
		return accountRepository.findById(id);
	}

	@Override
	public void checkAccountEditionData(EditAccountForm form, int minInputLenght) throws ValidationException {
		if (form.getFirstName().length() < minInputLenght || form.getLastName().length() < minInputLenght) {
			throw new ValidationException("registrationError.lengthFieldError");
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void updateAccount(EditAccountForm form) {
		accountRepository.updateAccount(form);
	}

	@Transactional
	@Override
	public void checkAccountRegistrationData(AddAccountForm form) throws ValidationException {
		checkInputLenght(form, 3, 8);
		Account account = accountRepository.findByLogin(form.getLogin());
		if (account != null) {
			throw new ValidationException("registrationError.loginExist");
		}
		account = accountRepository.findByEmail(form.getEmail());
		if (account != null) {
			throw new ValidationException("registrationError.emailExist");
		}
		if (!form.getPassword().equals(form.getConfirmPassword())) {
			throw new ValidationException("registrationError.notEqualPasswords");
		}
	}

	private void checkInputLenght(AddAccountForm form, int minInputLenght, int minPasswordLenght)
			throws ValidationException {
		Field[] fields = form.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType() != String.class) {
				continue;
			}
			field.setAccessible(true);
			try {
				if (((String) field.get(form)).length() < minInputLenght) {
					throw new ValidationException("registrationError.lengthFieldError");
				}
				if (field.getName().equals("password") && ((String) field.get(form)).length() < minPasswordLenght) {
					throw new ValidationException("registrationError.passwordLengthError");
				}
			} catch (IllegalArgumentException e) {
				LOGGER.error("Error into checkInputLenght method", e);
			} catch (IllegalAccessException e) {
				LOGGER.error("Error into checkInputLenght method", e);
			}
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void addAccount(AddAccountForm form) {
		accountRepository.addAccount(form);
	}

	@Transactional
	@Override
	public List<Account> searchAccountsByLogin(HttpServletRequest req, String login, int limit, int offset) {
		List<Account> result = accountRepository.searchAccountsByLogin(login, limit, offset);
		int foundRecords = accountRepository.countFoundedRecords();
		req.setAttribute("foundRecords", foundRecords);
		return result;
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteAccount(long id) {
		accountRepository.deleteAccount(id);
	}

	@Transactional
	@Override
	public List<Book> findAllBooks(HttpServletRequest req, int limit, int offset) {
		List<Book> result = bookRepository.findAll(limit, offset);
		int foundRecords = bookRepository.countFoundedRecords();
		req.setAttribute("foundRecords", foundRecords);
		for (Book book : result) {
			book.setAuthors(authorRepository.findBookAuthors(book));
		}
		return result;
	}

	@Transactional
	@Override
	public Book findBookById(long id) {
		Book result = bookRepository.findById(id);
		result.setAuthors(authorRepository.findBookAuthors(result));
		return result;
	}

	@Transactional
	@Override
	public List<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	@Transactional(readOnly = false)
	@Override
	public void updateBook(EditBookForm form) {
		Book book = new Book(form.getId(), form.getName(), form.getPublisher(), form.getImprintYear(),
				form.getAlternativeName());
		bookRepository.updateBook(book);
		bookAuthorRepository.removeAllBookAuthors(book.getId());
		for (Long idAuthor : form.getAuthor()) {
			bookAuthorRepository.addBookAuthor(new BookAuthor(book.getId(), idAuthor));
		}
	}

	@Override
	public void checkDataAtBookForm(EditBookForm form) throws ValidationException {
		if (form.getName().length() == 0) {
			throw new ValidationException("validationError.bookNameInput");
		}
		if (form.getPublisher().length() == 0) {
			throw new ValidationException("validationError.bookPublisherInput");
		}
		if (form.getImprintYear() < 1564 || form.getImprintYear() > Calendar.getInstance().get(Calendar.YEAR)) {
			throw new ValidationException("validationError.bookYearInput");
		}
		if (form.getAuthor() == null) {
			throw new ValidationException("validationError.bookAuthorInput");
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteBook(long bookId) {
		bookRepository.deleteBook(bookId);
		LOGGER.info("Book created");
	}

	@Transactional(readOnly = false)
	@Override
	public void createBook(EditBookForm form) {
		Id idBook = bookRepository.createBook(form);
		for (Long idAuthor : form.getAuthor()) {
			bookAuthorRepository.addBookAuthor(new BookAuthor(idBook.getId(), idAuthor));
		}
		LOGGER.info("New book created");
	}

	@Transactional
	@Override
	public List<Author> findAllAuthors(HttpServletRequest req, int limit, int offset) {
		List<Author> result = authorRepository.findAll(limit, offset);
		for (Author author : result) {
			author.setBooks(bookRepository.searchAuthorBooks(author.getId()));
		}
		int foundRecords = authorRepository.countFoundedRecords();
		req.setAttribute("foundRecords", foundRecords);
		return result;
	}

	@Override
	public void checkDataAtAuthorForm(AuthorForm form) throws ValidationException {
		if (form.getFirstName().length() == 0 || form.getLastName().length() == 0) {
			throw new ValidationException("validationError.authorInputLenght");
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void createAuthor(AuthorForm form) {
		authorRepository.createAuthor(form);
		LOGGER.info("New author created");
	}
}
