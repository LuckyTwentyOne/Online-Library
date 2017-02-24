package ua.nure.butov.summaryTask4.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "ErrorHandler")
public class ErrorHandler extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(req, resp);
		} catch (Throwable th) {
			LOGGER.error("Error during request: " + req.getRequestURI(), th);
			resp.sendRedirect("/SummaryTask4/error");
		}

	}

}
