package ua.nure.butov.summaryTask4.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.model.Account;

/**
 * Filter determines which URLs are accessible 
 * for each role in system.
 * 
 * @author V. Butov
 *
 */
@WebFilter(filterName = "AccessFilter")
public class AccessFilter extends AbstractFilter {

	@Override
	public final void doFilter(final HttpServletRequest req,
							   final HttpServletResponse resp, final FilterChain chain)
							   throws IOException, ServletException {
		Account account = (Account) req.getSession().
				getAttribute(Constants.CURRENT_ACCOUNT);
		String currentUrl = req.getRequestURI();
		if (account.getIdRole() == 1 && notForAdminURL(currentUrl)) {
			resp.sendRedirect("/SummaryTask4/admin/home");
		} else if (account.getIdRole() == 2 && notForLibrarianURL(currentUrl)) {
			resp.sendRedirect("/SummaryTask4/librarian/home");
		} else if (account.getIdRole() == 3 && notForReaderURL(currentUrl)) {
			resp.sendRedirect("/SummaryTask4/reader/home");
		} else {
			chain.doFilter(req, resp);
		}
	}

	/**
	 * Determines forbiden URLs for admin.
	 * 
	 * @param currentUrl
	 * @return true if current URL is forbiden
	 */
	private boolean notForAdminURL(String currentUrl) {
		if (currentUrl.startsWith("/SummaryTask4/librarian/")) {
			return true;
		}
		if (currentUrl.startsWith("/SummaryTask4/reader/")) {
			return true;
		}
		return false;
	}

	/**
	 *  Determines forbiden URLs for librarian.
	 * 
	 * @param currentUrl
	 * @return true if current URL is forbiden
	 */
	private boolean notForLibrarianURL(String currentUrl) {
		if (currentUrl.startsWith("/SummaryTask4/admin/")) {
			return true;
		}
		if (currentUrl.startsWith("/SummaryTask4/reader/")) {
			return true;
		}
		return false;
	}

	/**
	 * Determines forbiden URLs for reader.
	 * 
	 * @param currentUrl
	 * @return true if current URL is forbiden
	 */
	private boolean notForReaderURL(String currentUrl) {
		if (currentUrl.startsWith("/SummaryTask4/admin/")) {
			return true;
		}
		if (currentUrl.startsWith("/SummaryTask4/librarian/")) {
			return true;
		}
		return false;
	}
}