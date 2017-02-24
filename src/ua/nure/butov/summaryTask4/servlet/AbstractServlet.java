package ua.nure.butov.summaryTask4.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.annotation.Array;
import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.service.AdminService;
import ua.nure.butov.summaryTask4.service.CommonService;
import ua.nure.butov.summaryTask4.service.I18nService;
import ua.nure.butov.summaryTask4.service.LibrarianService;
import ua.nure.butov.summaryTask4.service.MailService;
import ua.nure.butov.summaryTask4.service.ReaderService;
import ua.nure.butov.summaryTask4.service.SocialService;
import ua.nure.butov.summaryTask4.serviceImpl.ServiceManager;

/**
 * A base servlet for all servlets. Provides access to services.
 */
public abstract class AbstractServlet extends HttpServlet {
	private static final long serialVersionUID = 6609272127675998466L;

	protected final Logger LOGGER = Logger.getLogger(getClass());
	private ServiceManager serviceManager;

	@Override
	public final void init() throws ServletException {
		serviceManager = ServiceManager.getInstance(getServletContext());
		initServlet();
	}

	protected void initServlet() throws ServletException {
	}

	@Override
	public final void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public final CommonService getCommonService() {
		return serviceManager.getCommonService();
	}
	
	public final I18nService getI18nService(){
		return serviceManager.getI18nService();
	}

	public final AdminService getAdminService() {
		return serviceManager.getAdminService();
	}
	
	public final ReaderService getReaderService() {
		return serviceManager.getReaderService();
	}
	
	public final LibrarianService getLibrarianService(){
		return serviceManager.getLibrarianService();
	}
	
	public final MailService getMailService(){
		return serviceManager.getMailService();
	}
	
	public final SocialService getSocialService(){
		return serviceManager.getSocialService();
	}
	
	public final String getApplicationProperty(String key) {
		return serviceManager.getApplicationProperty(key);
	}

	protected void forwardToPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("currentPage", page);
		req.getRequestDispatcher("/WEB-INF/view/page-template.jsp").forward(req, resp);
	}
	
	protected Locale getSessionLocale(HttpServletRequest req) {
			return (Locale) req.getAttribute(Constants.SESSION_LOCALE);
	}

	protected <T> T createForm(Class<T> formClass, HttpServletRequest req) {
		try {
			T form = formClass.newInstance();
			Field[] fields = formClass.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if(field.getAnnotation(Array.class)!=null){
					String[]values = req.getParameterValues(field.getName());
					if (values != null) {
						Object convertedValue = convertArray(field.getType(), values);
						field.set(form, convertedValue);
					}
				}else{					
					String value = req.getParameter(field.getName());
					if (value != null) {
						Object convertedValue = convert(field.getType(), value);
						field.set(form, convertedValue);
					}
				}	
			}
			return form;
		} catch (InstantiationException | IllegalAccessException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Object convertArray(Class<?> type, String[]values){
		if (type.getName().equals("[Ljava.lang.Long;")) {
			Long[] result = new Long[values.length];
			for(int i=0; i<values.length;i++){
				result[i]= new Long(values[i]);
			}
			return result;
		}else {
			throw new IllegalArgumentException("Can't convert to " + type);
		}
	}
	

	private Object convert(Class<?> type, String value) {
		if (type == String.class) {
			return value;
		} else if (type == Integer.TYPE) {
			if (value.equals("")) {
				return Integer.parseInt("0");
			} else {
				return Integer.parseInt(value);
			}
		} else if (type == Boolean.TYPE) {
			return Boolean.valueOf(value);
		}else if (type == Boolean.class) {
				return new Boolean(value);	
		} else if (type == Long.TYPE) {
			return Long.parseLong(value);
		} else if (type == Long.class) {
			return new Long(value);
		} else {
			throw new IllegalArgumentException("Can't convert to " + type);
		}
	}
}
