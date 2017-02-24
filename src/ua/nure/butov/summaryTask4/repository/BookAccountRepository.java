package ua.nure.butov.summaryTask4.repository;

import java.sql.Timestamp;
import java.util.List;

import ua.nure.butov.summaryTask4.model.BookAccount;

/**
 * Repository for account-book(order) relation.
 * 
 * @author V.Butov
 *
 */
public interface BookAccountRepository {

	/**
	 * Number of records needed to be processed.
	 * 
	 * @return number of found records
	 */
	long countUnprocessedOrders();

	
	List<BookAccount> findAll();
	
	/**
	 * Finds all records where current time 
	 * is later than return time.
	 * 
	 * @return list of orders
	 */
	List<BookAccount> findAllDebtors();
	
	void addFine(long bookAccountId, double fine);
	
	BookAccount findById(long orderId);
	
	void processNewOrder(long orderId, String borrowType, Timestamp returnTime);
	
	void deleteOrder(long idOrder);
	
	void createNewOrder(long idAccount, long idBook);
	
	/**
	 * Finds all orders for certain account
	 * 
	 * @param idAccount 
	 * @return list of orders
	 */
	List<BookAccount> findAllForAccount(long idAccount);

}
