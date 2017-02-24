package ua.nure.butov.summaryTask4.repository;

import java.util.List;

import ua.nure.butov.summaryTask4.form.AddAccountForm;
import ua.nure.butov.summaryTask4.form.EditAccountForm;
import ua.nure.butov.summaryTask4.form.RegistrationForm;
import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.model.Id;
import ua.nure.butov.summaryTask4.model.SocialAccount;

/**
 * Repository for Accounts.
 * 
 * @author Viacheslav Butov
 *
 */
public interface AccountRepository {

	/**
	 * Finds record by 'login' and converts
	 * it into Account model.
	 * 
	 * @param login account's login
	 * @return Account object
	 */
	Account findByLogin(String login);

	/**
	 * Finds list of records.
	 * 
	 * @param limit the number of results returned
	 * @param offset the number records to pass from begining
	 * @return  list of records
	 */
	List<Account> findAll(int limit, int offset);

	/**
	 * Finds record by 'id' and converts
	 * it into Account model.
	 * 
	 * @param id account's id
	 * @return Account object
	 */
	Account findById(long id);

	/**
	 * Finds record by 'email' and converts
	 * it into Account model.
	 * 
	 * @param email account's email
	 * @return Account object
	 */
	Account findByEmail(String email);

	/**
	 * Inserts new record into database.
	 * 
	 * @param form user's input
	 * @return Account according to form data
	 */
	Account register(RegistrationForm form);

	/**
	 * Updates account data.
	 * 
	 * @param form 
	 */
	void updateAccount(EditAccountForm form);

	/**
	 * Adds new record into database
	 * @param form 
	 * @return id of just created record
	 */
	Id addAccount(AddAccountForm form);

	/**
	 * Searches all records which match search query.
	 * 
	 * @param login search query
	 * @param limit the number of results returned
	 * @param offset the number records to pass from begining
	 * @return search result
	 */
	List<Account> searchAccountsByLogin(String login, int limit, int offset);

	/**
	 * Deletes record.
	 * 
	 * @param id account's id which you want to delete
	 */
	void deleteAccount(long id);
	
	/**
	 * Number of found records
	 * 
	 * @return number of found records
	 */
	int countFoundedRecords();
	
	/**
	 * Changes account 'active' condition on {@code true}
	 * 
	 * @param idAccount
	 */
	void activateAccount(long idAccount);
	
	/**
	 * Creates new record according to social account.
	 * 
	 * @param socialAccount 
	 * @return converted Account object
	 */
	Account registerBySocialAccount(SocialAccount socialAccount);
}
