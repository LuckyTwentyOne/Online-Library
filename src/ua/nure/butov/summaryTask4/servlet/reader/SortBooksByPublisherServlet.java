package ua.nure.butov.summaryTask4.servlet.reader;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = { "/reader/main/sortByPublisher", "/main/sortByPublisher" })
public class SortBooksByPublisherServlet extends AbstractServlet {
	private static final long serialVersionUID = 6071300428245450508L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int limit = Constants.DEFAULT_ROW_LIMIT_FOR_READER;
		if (req.getParameter("page") != null) {
			page = Integer.valueOf(req.getParameter("page"));
		}
		List<Book> booksList = getReaderService().findAllBooksSortedByPublisher(req, limit, (page - 1) * limit);
		int totalRecords = (int) req.getAttribute("foundRecords");
		int neededPages = getCommonService().countNeededPages(totalRecords, limit);
		req.setAttribute("curURI", req.getRequestURI());
		if (req.getQueryString() != null) {
			req.setAttribute("query", "?" + req.getQueryString().replaceAll("&page=[\\d]+", ""));
		} else {
			req.setAttribute("query", "?");
		}
		req.setAttribute("booksList", booksList);
		req.setAttribute("neededPages", neededPages);
		req.setAttribute("pageNumber", page);
		forwardToPage("reader/home.jsp", req, resp);
	}
}
