package ua.nure.butov.summaryTask4.tag;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.serviceImpl.ServiceManager;

public class ResourseBundleReaderTag extends TagSupport {
	private static final long serialVersionUID = -4641583315147784200L;

	private static final Logger LOGGER = Logger.getLogger(ResourseBundleReaderTag.class);

	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
		LOGGER.debug("ResourseBundleReaderTag key = " + key);
	}

	@Override
	public int doStartTag() throws JspException {
		LOGGER.debug("Custom tag ResourseBundleReaderTag started.");
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			ServiceManager serviceManager = ServiceManager.getInstance(request.getServletContext());
			Locale sessionLocale = (Locale) request.getSession().getAttribute(Constants.SESSION_LOCALE);
			String value = serviceManager.getI18nService().getMessage(key, sessionLocale);
			out.print(value);
			LOGGER.debug("Custom tag ResourseBundleReaderTag finished successfully.");
		} catch (IOException e) {
			LOGGER.error("Exception into tag " + this.getClass(), e);
		}
		return SKIP_BODY;
	}
	
	
}
