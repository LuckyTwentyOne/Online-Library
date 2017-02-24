package ua.nure.butov.summaryTask4.repository;

import java.util.List;

import ua.nure.butov.summaryTask4.form.EditBookForm;
import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.model.Id;

/**
 * Repository for books.
 * 
 * @author V.Butov
 *
 */
public interface BookRepository {

	List<Book> findAll(int limit, int offset);

	int countFoundedRecords();

	Book findById(long id);

	/**
	 * Updates book record.
	 * 
	 * @param book
	 *            model with changed data.
	 */
	void updateBook(Book book);

	void deleteBook(long bookId);

	Id createBook(EditBookForm form);

	List<Book> searchBooksByName(String bookName, int limit, int offset);

	List<Book> searchBooksByAuthor(String authorSurname, int limit, int offset);

	List<Book> sortByName(int limit, int offset);

	List<Book> sortByAuthor(int limit, int offset);

	List<Book> sortByPublisher(int limit, int offset);

	List<Book> sortByYear(int limit, int offset);

	List<Book> searchAuthorBooks(long idAuthor);

	void updateBookTotalOrdersCount(long idBook);

	int getTotalOrdersNumber();
	
	void updatePercent();
}
