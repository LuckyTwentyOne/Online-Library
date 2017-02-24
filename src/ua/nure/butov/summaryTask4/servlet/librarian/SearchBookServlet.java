package ua.nure.butov.summaryTask4.servlet.librarian;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = { "/librarian/searchBook" })
public class SearchBookServlet extends AbstractServlet {
	private static final long serialVersionUID = 6071300428245450508L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String searchQuery = req.getParameter("query");
		String searchBy = req.getParameter("searchBy");
		List<Book> booksList = null;
		if (searchBy.equals("name")) {
			booksList = getLibrarianService().searchBooksByName(req, searchQuery, 50, 0);
		}else if(searchBy.equals("author")){
			booksList = getLibrarianService().searchBooksByAuthor(req, searchQuery, 50, 0);
		}
		long unprocessedOrders = getLibrarianService().countUnprocessedOrders();
		req.setAttribute("unprocessedOrders", unprocessedOrders);
		req.setAttribute("booksList", booksList);
		forwardToPage("librarian/home.jsp", req, resp);
	}
}
