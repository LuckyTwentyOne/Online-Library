package ua.nure.butov.summaryTask4.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.model.BookAccount;

/**
 * 
 * Provides functionality for application librarians.
 * 
 * @author V.Butov
 *
 */
public interface LibrarianService extends Service {
	
	void addBookCopy(int idBook);
	
	List<Book> findAllBooks(HttpServletRequest req, int limit, int offset);
	
	List<Book> searchBooksByName(HttpServletRequest req, String bookName, int limit, int offset);
	
	List<Book> searchBooksByAuthor(HttpServletRequest req, String authorSurname, int limit, int offset);
	
	long countUnprocessedOrders();
	
	List<BookAccount> findAllOrders();
	
	void countFineForAllDebtors();
	
	BookAccount findOrderById(long orderId);
	
	void processNewOrderForm(HttpServletRequest req) throws ValidationException;
	
	void closeOrder(long idOrder);
}
