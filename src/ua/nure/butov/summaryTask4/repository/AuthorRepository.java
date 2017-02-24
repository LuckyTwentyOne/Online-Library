package ua.nure.butov.summaryTask4.repository;

import java.util.List;
import ua.nure.butov.summaryTask4.form.AuthorForm;
import ua.nure.butov.summaryTask4.model.Author;
import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.model.Id;

/**
 * Repository for authors.
 * 
 * @author Viacheslav Butov
 *
 */
public interface AuthorRepository {

	/**
	 * Finds all authors of the book.
	 * @param book 
	 * @return	book's authors list
	 */
	List<Author> findBookAuthors(Book book);

	/**
	 * Number of found records
	 * 
	 * @return number of found records
	 */
	int countFoundedRecords();
	
	
	/**
	 * Finds all authors without limits.
	 * @return list of Authors
	 */
	List<Author> findAll();
	
	/**
	 * Finds list of records.
	 * 
	 * @param limit the number of results returned
	 * @param offset the number records to pass from begining
	 * @return  list of records
	 */
	List<Author> findAll(int limit, int offset);
	
	/**
	 * Adds new Author into database
	 * @param form 
	 * @return id of just created Author
	 */
	Id createAuthor(AuthorForm form);
}
