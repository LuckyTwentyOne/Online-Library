package ua.nure.butov.summaryTask4.serviceImpl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.service.MailService;

/**
 * Implementation of {@link MailService} interface using gMail.
 * 
 * 
 * @author V.Butov
 *
 */
class MailServiceImpl implements MailService {

	private static final Logger LOGGER = Logger.getLogger(MailServiceImpl.class);
	private final Session SESSION;

	/**
	 * Setting all needed properties for mail service.
	 * 
	 * @param serviceManager
	 */
	MailServiceImpl(ServiceManager serviceManager) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", serviceManager.getApplicationProperty("mail.host"));
		props.put("mail.smtp.port", serviceManager.getApplicationProperty("mail.port"));
		props.put("mail.smtp.socketFactory.port", serviceManager.getApplicationProperty("mail.socketFactory.port"));
		props.put("mail.smtp.socketFactory.class", serviceManager.getApplicationProperty("mail.socketFactory.class"));
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(serviceManager.getApplicationProperty("mail.address"),
						serviceManager.getApplicationProperty("mail.password"));
			}
		};
		SESSION = Session.getDefaultInstance(props, authenticator);
	}

	@Override
	public boolean send(String subject, String text, String fromEmail, String toEmail) {
		try {
			Message message = new MimeMessage(SESSION);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
			LOGGER.info("email was sent to "+toEmail);
			return true;	
		} catch (MessagingException e) {
			LOGGER.error("Mail was not sent. " + e.getMessage());
			return false;
		}
	}

}
