package ua.nure.butov.summaryTask4.service;

/**
 * Service for sending emails.
 * 
 * @author V.Butov
 *
 */
public interface MailService {
	
	public boolean send(String subject, String text, String fromEmail, String toEmail);

}
