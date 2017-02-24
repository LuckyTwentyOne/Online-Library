package ua.nure.butov.summaryTask4.repository;

import ua.nure.butov.summaryTask4.model.BookAuthor;
import ua.nure.butov.summaryTask4.model.Id;

/**
 * Repository for book-author relation.
 * 
 * @author V.Butov
 *
 */
public interface BookAuthorRepository {

	/**
	 * Adds record which connects book with author.
	 * 
	 * @param bookAuthor model
	 * @return id of just created record
	 */
	Id addBookAuthor(BookAuthor bookAuthor);
	
	/**
	 * Removes all authors connected with current book.
	 * 
	 * @param bookId
	 */
	void removeAllBookAuthors(long bookId);
}
