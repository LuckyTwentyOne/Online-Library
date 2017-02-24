package ua.nure.butov.summaryTask4.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.constants.Constants;

@WebServlet(urlPatterns = "/switch")
public class SwitchLanguageServlet extends AbstractServlet {
	private static final long serialVersionUID = 6071300428245450508L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Locale locale = (Locale) req.getSession().getAttribute(Constants.SESSION_LOCALE);
		if (locale == null) {
			locale = req.getLocale();
		}
		if (!locale.getLanguage().equals("en")) {
			locale = new Locale("en");
		} else {
			locale = new Locale("ru");
		}
		req.getSession().setAttribute(Constants.SESSION_LOCALE, locale);
		resp.sendRedirect("login");
	}
}
