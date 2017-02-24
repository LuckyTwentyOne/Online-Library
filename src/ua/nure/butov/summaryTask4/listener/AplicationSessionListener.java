package ua.nure.butov.summaryTask4.listener;

import javax.servlet.annotation.WebListener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

@WebListener
public class AplicationSessionListener implements HttpSessionListener {
	private static final Logger LOGGER = Logger.getLogger(AplicationSessionListener.class);

	public void sessionCreated(HttpSessionEvent se) {
		LOGGER.debug(se.getSession().getId() + " session created");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		LOGGER.debug(se.getSession().getId() + " session destroyed");
	}

}
