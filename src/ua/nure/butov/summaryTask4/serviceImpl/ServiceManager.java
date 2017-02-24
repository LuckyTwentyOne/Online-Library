package ua.nure.butov.summaryTask4.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import ua.nure.butov.summaryTask4.exception.FileException;
import ua.nure.butov.summaryTask4.repository.AccountRegistrationRepository;
import ua.nure.butov.summaryTask4.repository.AccountRepository;
import ua.nure.butov.summaryTask4.repository.AuthorRepository;
import ua.nure.butov.summaryTask4.repository.BookAccountRepository;
import ua.nure.butov.summaryTask4.repository.BookAuthorRepository;
import ua.nure.butov.summaryTask4.repository.BookRepository;
import ua.nure.butov.summaryTask4.repository.CopyRepository;
import ua.nure.butov.summaryTask4.repository.impl.AccountRegistrationRepositoryImpl;
import ua.nure.butov.summaryTask4.repository.impl.AccountRepositoryImpl;
import ua.nure.butov.summaryTask4.repository.impl.AuthorRepositoryImpl;
import ua.nure.butov.summaryTask4.repository.impl.BookAccountRepositoryImpl;
import ua.nure.butov.summaryTask4.repository.impl.BookAuthorRepositoryImpl;
import ua.nure.butov.summaryTask4.repository.impl.BookRepositoryImpl;
import ua.nure.butov.summaryTask4.repository.impl.CopyRepositoryImpl;
import ua.nure.butov.summaryTask4.service.AdminService;
import ua.nure.butov.summaryTask4.service.CommonService;
import ua.nure.butov.summaryTask4.service.I18nService;
import ua.nure.butov.summaryTask4.service.LibrarianService;
import ua.nure.butov.summaryTask4.service.MailService;
import ua.nure.butov.summaryTask4.service.ReaderService;
import ua.nure.butov.summaryTask4.service.SocialService;
import ua.nure.butov.summaryTask4.task.CalculateFinesTask;

/**
 * Class which configurates the whole system.
 * 
 * Here you can configurate which implementation of repository and which
 * implementation of buisness services to use.
 * <p>
 * Provides access to services in application.
 * 
 * @author Viacheslav Butov
 *
 */
public final class ServiceManager {

	private static final Logger LOGGER = Logger.getLogger(ServiceManager.class);

	/**
	 * Name of variable into context servlet.
	 */
	private static final String SERVICE_MANAGER = "SERVICE_MANAGER";

	/**
	 * Singleton instance for current application.
	 * 
	 * @param context
	 *            context for current application
	 * @return ServiceManager singleton instance
	 */
	public static ServiceManager getInstance(ServletContext context) {
		ServiceManager instance = (ServiceManager) context.getAttribute(SERVICE_MANAGER);
		if (instance == null) {
			instance = new ServiceManager();
			context.setAttribute(SERVICE_MANAGER, instance);
		}
		return instance;
	}

	private final Properties applicationProperties = new Properties();
	private final AccountRepository accountRepository;
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final BookAuthorRepository bookAuthorRepository;
	private final BookAccountRepository bookAccountRepository;
	private final CopyRepository copyRepository;
	private final AccountRegistrationRepository accountRegistrationRepository;
	private final LibrarianService librarianService;
	private final CommonService commonService;
	private final AdminService adminService;
	private final ReaderService readerService;
	private final I18nService i18nService;
	private final MailService mailService;
	private final SocialService socialService;
	private final ScheduledExecutorService executorService;
	private final BasicDataSource dataSource;

	/**
	 * Constructor where you can configure this app.
	 */
	private ServiceManager() {
		loadApplicationProperties();
		socialService = new FacebookSocialService(this);
		mailService = new MailServiceImpl(this);
		dataSource = buildDataSource();
		i18nService = new I18nServiceImpl();
		executorService = Executors.newSingleThreadScheduledExecutor();
		accountRepository = new AccountRepositoryImpl();
		bookRepository = new BookRepositoryImpl();
		authorRepository = new AuthorRepositoryImpl();
		copyRepository = new CopyRepositoryImpl();
		bookAuthorRepository = new BookAuthorRepositoryImpl();
		bookAccountRepository = new BookAccountRepositoryImpl();
		accountRegistrationRepository = new AccountRegistrationRepositoryImpl();
		commonService = ServiceFactory.createService(dataSource,
				new CommonServiceImpl(accountRepository, accountRegistrationRepository));
		adminService = ServiceFactory.createService(dataSource,
				new AdminServiceImpl(accountRepository, bookRepository, authorRepository, bookAuthorRepository));
		readerService = ServiceFactory.createService(dataSource,
				new ReaderServiceImpl(bookRepository, authorRepository, copyRepository, bookAccountRepository));
		librarianService = ServiceFactory.createService(dataSource,
				new LibrarianServiceImpl(copyRepository, bookRepository, authorRepository, bookAccountRepository));
		startSheduledCalculateFinesTask(executorService);
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public ReaderService getReaderService() {
		return readerService;
	}

	public LibrarianService getLibrarianService() {
		return librarianService;
	}

	public I18nService getI18nService() {
		return i18nService;
	}

	public ScheduledExecutorService getExecutorService() {
		return executorService;
	}

	public MailService getMailService() {
		return mailService;
	}

	public SocialService getSocialService() {
		return socialService;
	}

	public String getApplicationProperty(String key) {
		return applicationProperties.getProperty(key);
	}

	/**
	 * Closes all external resources.
	 * 
	 */
	public void shutdown() {
		try {
			dataSource.close();
			executorService.shutdownNow();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Builds DataSource from context.xml file.
	 * 
	 * @return DataSource
	 */
	private BasicDataSource buildDataSource() {
		BasicDataSource ds = null;
		// DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (BasicDataSource) ctx.lookup("java:comp/env/jdbc/summarytask4");
		} catch (NamingException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return ds;
	}

	private void loadApplicationProperties() {
		try (InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("/application.properties")) {
			applicationProperties.load(in);
		} catch (IOException e) {
			LOGGER.error("Cannot load file: 'application.properties'");
			throw new FileException("Cannot load file: 'application.properties'", e);
		}
	}

	private void startSheduledCalculateFinesTask(ScheduledExecutorService executorService) {
		executorService.scheduleAtFixedRate(new CalculateFinesTask(this), 0, 30, TimeUnit.MINUTES);
	}
}
