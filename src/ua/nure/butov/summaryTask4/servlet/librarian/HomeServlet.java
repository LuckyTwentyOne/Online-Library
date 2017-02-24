package ua.nure.butov.summaryTask4.servlet.librarian;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = { "/librarian/home","/librarian"})
public class HomeServlet extends AbstractServlet {
	private static final long serialVersionUID = 6071300428245450508L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int limit = Constants.DEFAULT_ROW_LIMIT_FOR_READER;
		if (req.getParameter("page") != null&&!req.getParameter("page").equals("")) {
			page = Integer.valueOf(req.getParameter("page"));
		}
		long unprocessedOrders = getLibrarianService().countUnprocessedOrders();
		List<Book> booksList = getLibrarianService().findAllBooks(req, limit, (page - 1) * limit);
		int totalRecords = (int) req.getAttribute("foundRecords");
		int neededPages = getCommonService().countNeededPages(totalRecords, limit);
		req.setAttribute("unprocessedOrders", unprocessedOrders);
		req.setAttribute("booksList", booksList);
		req.setAttribute("neededPages", neededPages);
		req.setAttribute("pageNumber", page);
		forwardToPage("librarian/home.jsp", req, resp);
	}
}
