package ua.nure.butov.summaryTask4.repository;

/**
 * Repository for copies.
 * <p>
 * Copy is an instance of book.
 * 
 * @author V.Butov
 *
 */
public interface CopyRepository {
	
	void addBookCopy(int idBook);
	
	long countBookCopies(long idBook);
	
	long countFreeBookCopies(long idBook);
	
	void setFreeOneBookCopy(long idBook);
	
	void setBusyOneBookCopy(long idBook);
}
