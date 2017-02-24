package ua.nure.butov.summaryTask4.serviceImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.annotation.Transactional;
import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.model.BookAccount;
import ua.nure.butov.summaryTask4.repository.AuthorRepository;
import ua.nure.butov.summaryTask4.repository.BookAccountRepository;
import ua.nure.butov.summaryTask4.repository.BookRepository;
import ua.nure.butov.summaryTask4.repository.CopyRepository;
import ua.nure.butov.summaryTask4.service.LibrarianService;

/**
 * Implementation of {@link LibrarianService} interface.
 * 
 * @author V.Butov
 *
 */
class LibrarianServiceImpl implements LibrarianService {
	private static final Logger LOGGER = Logger.getLogger(LibrarianServiceImpl.class);
	private static final int HOUR_IN_MS = 3600000;

	private final CopyRepository copyRepository;
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final BookAccountRepository bookAccountRepository;

	LibrarianServiceImpl(CopyRepository copyRepository, BookRepository bookRepository,
			AuthorRepository authorRepository, BookAccountRepository bookAccountRepository) {
		this.copyRepository = copyRepository;
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.bookAccountRepository = bookAccountRepository;
	}

	@Transactional(readOnly = false)
	@Override
	public void addBookCopy(int idBook) {
		copyRepository.addBookCopy(idBook);
	}

	@Transactional
	@Override
	public List<Book> findAllBooks(HttpServletRequest req, int limit, int offset) {
		List<Book> result = bookRepository.findAll(limit, offset);
		int foundRecords = bookRepository.countFoundedRecords();
		req.setAttribute("foundRecords", foundRecords);
		for (Book book : result) {
			book.setAuthors(authorRepository.findBookAuthors(book));
			book.setNumberOfCopies(copyRepository.countBookCopies(book.getId()));
		}
		return result;
	}

	@Transactional
	@Override
	public List<Book> searchBooksByName(HttpServletRequest req, String bookName, int limit, int offset) {
		List<Book> result = bookRepository.searchBooksByName(bookName, limit, offset);
		int foundRecords = bookRepository.countFoundedRecords();
		req.setAttribute("foundRecords", foundRecords);
		for (Book book : result) {
			book.setAuthors(authorRepository.findBookAuthors(book));
			book.setNumberOfCopies(copyRepository.countBookCopies(book.getId()));
		}
		return result;
	}

	@Transactional
	@Override
	public List<Book> searchBooksByAuthor(HttpServletRequest req, String authorSurname, int limit, int offset) {
		List<Book> result = bookRepository.searchBooksByAuthor(authorSurname, limit, offset);
		int foundRecords = bookRepository.countFoundedRecords();
		req.setAttribute("foundRecords", foundRecords);
		for (Book book : result) {
			book.setAuthors(authorRepository.findBookAuthors(book));
			book.setNumberOfCopies(copyRepository.countBookCopies(book.getId()));
		}
		return result;
	}

	@Transactional
	@Override
	public long countUnprocessedOrders() {
		return bookAccountRepository.countUnprocessedOrders();
	}

	@Transactional
	@Override
	public List<BookAccount> findAllOrders() {
		return bookAccountRepository.findAll();
	}

	@Transactional(readOnly = false)
	@Override
	public void countFineForAllDebtors() {
		List<BookAccount> orders = bookAccountRepository.findAllDebtors();
		for (BookAccount order : orders) {
			long currentTime = new Date().getTime();
			long returnTime = order.getReturnTime().getTime();
			int hours = (int) ((currentTime - returnTime) / HOUR_IN_MS) + 1;
			double fine = Constants.FINE_PER_HOUR * hours;
			bookAccountRepository.addFine(order.getId(), fine);
		}
	}

	@Transactional
	@Override
	public BookAccount findOrderById(long orderId) {
		return bookAccountRepository.findById(orderId);
	}

	@Transactional(readOnly = false)
	@Override
	public void processNewOrderForm(HttpServletRequest req) throws ValidationException {
		long idOrder = Long.valueOf(req.getParameter("id"));
		String borrowType = req.getParameter("borrowType");
		String time = req.getParameter("returnTime");
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		try {
			Date date = format.parse(time);
			if (date.getTime() < new Date().getTime()) {
				throw new ValidationException("validationException.dateFormat");
			}
			Timestamp returnTime = new Timestamp(date.getTime());
			bookAccountRepository.processNewOrder(idOrder, borrowType, returnTime);
			BookAccount order = bookAccountRepository.findById(idOrder);
			bookRepository.updateBookTotalOrdersCount(order.getIdBook());
			bookRepository.updatePercent();
		} catch (ParseException e) {
			throw new ValidationException("validationException.dateFormat");
		}

	}

	@Transactional(readOnly = false)
	@Override
	public void closeOrder(long idOrder) {
		BookAccount order = bookAccountRepository.findById(idOrder);
		copyRepository.setFreeOneBookCopy(order.getIdBook());
		bookAccountRepository.deleteOrder(idOrder);
		LOGGER.info("Order #" + idOrder + " was closed");
	}

}
