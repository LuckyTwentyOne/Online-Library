package ua.nure.butov.summaryTask4.servlet.librarian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = { "/librarian/addCopy"})
public class AddCopyServlet extends AbstractServlet {
	private static final long serialVersionUID = 6071300428245450508L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		int idBook = Integer.valueOf(req.getParameter("idBook"));
		getLibrarianService().addBookCopy(idBook);
		resp.sendRedirect("/SummaryTask4/librarian/home?page="+page);
	}
}
