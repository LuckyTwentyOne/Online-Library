package ua.nure.butov.summaryTask4.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.form.LoginForm;
import ua.nure.butov.summaryTask4.form.RegistrationForm;
import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.model.SocialAccount;

/**
 * 
 * Provides common functionality for all application roles.
 * 
 * @author V.Butov
 *
 */
public interface CommonService extends Service{
	
	Account login(LoginForm form) throws ValidationException;
	
	void checkRegistrationData(RegistrationForm form) throws ValidationException;
	
	/**
	 * Redirects user request depends on its role.
	 * 
	 * @param account user's account
	 * @throws IOException
	 */
	void redirectDependsOnRole(Account account, HttpServletResponse resp) throws IOException ;
	
	void registerAccount(RegistrationForm form, String hash);
	
	/**
	 * Counts pages for pagination implementation.
	 * 
	 * @return number of needed pages
	 */
	int countNeededPages(int numberOfRecords, int recordsPerPage);
	
	/**
	 * Finds Account by code(hash) and activates it.
	 * 
	 * @param code hash
	 * @return activated account
	 */
	Account activateAccountByCode(String code);
	
	/**
	 * Sends Json request at Google reCaptcha sersice to pass bots check.
	 * 
	 * @param gRecaptchaResponse read from user's http request
	 * @throws ValidationException if user have not passed captcha check
	 */
	void verifyCaptcha(String gRecaptchaResponse) throws ValidationException;
	
	/**
	 * Authentificates by SocialAccount
	 * 
	 * @return application account model
	 */
	Account authentificate(SocialAccount socialAccount);
	
	/**
	 * Calculates the SHA-256 or another digest and returns the value as a hex string.
	 */
	String hash(String sourceString);
}
