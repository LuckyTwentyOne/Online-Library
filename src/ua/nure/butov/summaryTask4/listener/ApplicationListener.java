package ua.nure.butov.summaryTask4.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.serviceImpl.ServiceManager;

/**
 * Context listener.
 * 
 * @author V Butov
 * 
 */
@WebListener
public class ApplicationListener implements ServletContextListener {
	private static final Logger LOGGER = Logger.getLogger(ApplicationListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.debug("Servlet context initialization starts");
		ServiceManager.getInstance(sce.getServletContext());
		LOGGER.debug("Servlet context initialization finished");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.debug("Servlet context destruction starts");
		ServiceManager.getInstance(sce.getServletContext()).shutdown();
		LOGGER.debug("Servlet context destruction finished");
	}

}
