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
 * Filter closes protected pages from unregistred user.
 * 
 * @author V. Butov
 *
 */
@WebFilter(filterName = "CheckLoginFilter")
public class CheckLoginFilter extends AbstractFilter {

	@Override
	public final void doFilter(final HttpServletRequest req,
						 final HttpServletResponse resp, final FilterChain chain)
						 throws IOException, ServletException {
		Account account = (Account) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
		String currentUrl = req.getRequestURI();
		if(account==null && isProtectedUrl(currentUrl)) {
			resp.sendRedirect("/SummaryTask4/login");
		} else {
			chain.doFilter(req, resp);
		}

	}

	/**
	 * Determines is current URL ptotected.
	 * 
	 * @param currentUrl
	 * @return true if URL is protected
	 */
	private boolean isProtectedUrl(String currentUrl) {
		if (currentUrl.startsWith("/SummaryTask4/admin/")) {
			return true;
		}
		if (currentUrl.startsWith("/SummaryTask4/librarian/")) {
			return true;
		}
		if (currentUrl.startsWith("/SummaryTask4/reader/")) {
			return true;
		}
		return false;
	}
}
