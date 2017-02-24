package ua.nure.butov.summaryTask4.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Set request charset encoding to specific encoding.
 *
 * @author V.Butov
 */
@WebFilter(filterName = "CharacterEnCodingFilter")
public class CharacterEnCodingFilter extends AbstractFilter {

	@Override
	
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		chain.doFilter(req, resp);

	}

}
