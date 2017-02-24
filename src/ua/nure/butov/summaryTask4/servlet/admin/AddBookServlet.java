package ua.nure.butov.summaryTask4.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.form.EditBookForm;
import ua.nure.butov.summaryTask4.model.Author;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = { "/admin/addBook" })
public class AddBookServlet extends AbstractServlet {
	private static final long serialVersionUID = 1087524531290835835L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Author> authors = getAdminService().findAllAuthors();
		req.setAttribute("authors", authors);
		forwardToPage("admin/addBook.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EditBookForm form = createForm(EditBookForm.class, req);
		try {
			getAdminService().checkDataAtBookForm(form);
			getAdminService().createBook(form);
			req.getSession().setAttribute("createdBookMessage", form.getName());
			resp.sendRedirect("/SummaryTask4/admin/home/books");
		} catch (ValidationException e) {
			List<Author> authors = getAdminService().findAllAuthors();
			req.setAttribute("authors", authors);
			req.setAttribute("form", form);
			req.setAttribute("addBookErrorMessage", getI18nService().getMessage(e.getMessage(),  getSessionLocale(req)));
			forwardToPage("admin/addBook.jsp", req, resp);
		}
	}
}
