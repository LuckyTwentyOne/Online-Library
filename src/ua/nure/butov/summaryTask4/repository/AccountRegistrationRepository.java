package ua.nure.butov.summaryTask4.repository;

import ua.nure.butov.summaryTask4.model.AccountRegistration;

/**
 * Gives possibilities to manipulate the data in
 * account-repository relation.
 * 
 * @author V.Butov
 *
 */
public interface AccountRegistrationRepository {
	
	/**
	 * Inserts some hash to relative account.
	 *
	 * @param idAccount
	 * @param hash
	 */
	void addActivateHashToAccount(long idAccount, String hash);
	
	/**
	 * Finds record by code (hash) and converts
	 * it into AccountRegistration model.
	 * 
	 * @param code hash
	 * @return AccountRegistration object
	 */
	AccountRegistration findByCode(String code);
	
	
	/**
	 * Deletes record from database.
	 * 
	 * @param idAccount searches record by this parameter
	 */
	void deleteRecord(long idAccount);
}
