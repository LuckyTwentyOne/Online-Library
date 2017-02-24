package ua.nure.butov.summaryTask4.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.annotation.Transactional;
import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.exception.UsersException;
import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.model.BookAccount;
import ua.nure.butov.summaryTask4.repository.AuthorRepository;
import ua.nure.butov.summaryTask4.repository.BookAccountRepository;
import ua.nure.butov.summaryTask4.repository.BookRepository;
import ua.nure.butov.summaryTask4.repository.CopyRepository;
import ua.nure.butov.summaryTask4.service.ReaderService;

/**
 * Implementation of {@link ReaderService} interface.
 * 
 * @author V.Butov
 *
 */
class ReaderServiceImpl implements ReaderService {
	private static final int HOUR_IN_MS = 3600000;
	private static final Logger LOGGER = Logger.getLogger(ReaderServiceImpl.class);
	private Lock locker = new ReentrantLock();
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final CopyRepository copyRepository;
	private final BookAccountRepository bookAccountRepository;

	ReaderServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
			CopyRepository copyRepository, BookAccountRepository bookAccountRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.copyRepository = copyRepository;
		this.bookAccountRepository = bookAccountRepository;
	}

	@Transactional
	@Override
	public List<Book> searchBooksByName(HttpServletRequest req, String bookName, int limit, int offset) {
		List<Book> result = bookRepository.searchBooksByName(bookName, limit, offset);
		setFoundRecordsNumberIntoRequest(req);
		addAuthorsToBooks(result);
		return result;
	}

	@Transactional
	@Override
	public List<Book> searchBooksByAuthor(HttpServletRequest req, String authorSurname, int limit, int offset) {
		List<Book> result = bookRepository.searchBooksByAuthor(authorSurname, limit, offset);
		setFoundRecordsNumberIntoRequest(req);
		addAuthorsToBooks(result);
		return result;
	}

	@Transactional
	@Override
	public List<Book> findAllBooksSortedByName(HttpServletRequest req, int limit, int offset) {
		List<Book> result = bookRepository.sortByName(limit, offset);
		setFoundRecordsNumberIntoRequest(req);
		addAuthorsToBooks(result);
		return result;
	}

	@Transactional
	@Override
	public List<Book> findAllBooksSortedByAuthor(HttpServletRequest req, int limit, int offset) {
		List<Book> result = bookRepository.sortByAuthor(limit, offset);
		setFoundRecordsNumberIntoRequest(req);
		addAuthorsToBooks(result);
		return result;
	}

	private void addAuthorsToBooks(List<Book> books) {
		for (Book book : books) {
			book.setAuthors(authorRepository.findBookAuthors(book));
			book.setFreeCopies(copyRepository.countFreeBookCopies(book.getId()));
		}
	}

	private void setFoundRecordsNumberIntoRequest(HttpServletRequest req) {
		int foundRecords = bookRepository.countFoundedRecords();
		req.setAttribute("foundRecords", foundRecords);
	}

	@Transactional
	@Override
	public List<Book> findAllBooksSortedByPublisher(HttpServletRequest req, int limit, int offset) {
		List<Book> result = bookRepository.sortByPublisher(limit, offset);
		setFoundRecordsNumberIntoRequest(req);
		addAuthorsToBooks(result);
		return result;
	}

	@Transactional
	@Override
	public List<Book> findAllBooksSortedByYear(HttpServletRequest req, int limit, int offset) {
		List<Book> result = bookRepository.sortByYear(limit, offset);
		setFoundRecordsNumberIntoRequest(req);
		addAuthorsToBooks(result);
		return result;
	}

	@Transactional
	@Override
	public List<Book> findAllBooksSortedByDate(HttpServletRequest req, int limit, int offset) {
		List<Book> result = bookRepository.findAll(limit, offset);
		setFoundRecordsNumberIntoRequest(req);
		addAuthorsToBooks(result);
		return result;
	}

	@Transactional(readOnly = false)
	@Override
	public void makeOrder(long idAccount, long idBook) throws UsersException {
		try {
			locker.lock();
			long freeCopies = copyRepository.countFreeBookCopies(idBook);
			if (freeCopies == 0) {
				throw new UsersException("usersException.allCopiesBusy");
			}
			copyRepository.setBusyOneBookCopy(idBook);
			bookAccountRepository.createNewOrder(idAccount, idBook);
			LOGGER.info("New order created");
		} finally {
			locker.unlock();
		}

	}

	@Transactional(readOnly = false)
	@Override
	public List<BookAccount> getReaderOrders(long idAccount) {
		List<BookAccount> result = bookAccountRepository.findAllForAccount(idAccount);
		for (BookAccount order : result) {
			if (order.getReturnTime() != null) {
				long currentTime = new Date().getTime();
				long returnTime = order.getReturnTime().getTime();
				if (currentTime > returnTime) {
					int hours = (int) ((currentTime - returnTime) / HOUR_IN_MS) + 1;
					double fine = Constants.FINE_PER_HOUR * hours;
					bookAccountRepository.addFine(order.getId(), fine);
				}
			}
		}
		return result;
	}

}
