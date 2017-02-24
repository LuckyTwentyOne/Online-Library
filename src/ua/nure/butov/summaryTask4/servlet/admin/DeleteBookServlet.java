package ua.nure.butov.summaryTask4.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = { "/admin/deleteBook" })
public class DeleteBookServlet extends AbstractServlet {
	private static final long serialVersionUID = 1087524531290835835L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long bookId = Long.valueOf(req.getParameter("id"));
		Book book = getAdminService().findBookById(bookId);
		getAdminService().deleteBook(bookId);
		req.getSession().setAttribute("deletedBook", book.getName());
		resp.sendRedirect("/SummaryTask4/admin/home/books");
	}
}
