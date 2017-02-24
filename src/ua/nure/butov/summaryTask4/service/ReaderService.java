package ua.nure.butov.summaryTask4.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ua.nure.butov.summaryTask4.exception.UsersException;
import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.model.BookAccount;

/**
 * 
 * Provides functionality for application reader role.
 * 
 * @author V.Butov
 *
 */
public interface ReaderService extends Service {

	List<Book> searchBooksByName(HttpServletRequest req, String bookName, int limit, int offset);
	
	List<Book> searchBooksByAuthor(HttpServletRequest req, String authorSurname, int limit, int offset);
	
	List<Book> findAllBooksSortedByDate(HttpServletRequest req, int limit, int offset);
	
	List<Book> findAllBooksSortedByName(HttpServletRequest req, int limit, int offset);
	
	List<Book> findAllBooksSortedByAuthor(HttpServletRequest req, int limit, int offset);
	
	List<Book> findAllBooksSortedByPublisher(HttpServletRequest req, int limit, int offset);
	
	List<Book> findAllBooksSortedByYear(HttpServletRequest req, int limit, int offset);
	
	void makeOrder(long idAccount, long idBook) throws UsersException;
	
	List<BookAccount> getReaderOrders(long idAccount);
}
