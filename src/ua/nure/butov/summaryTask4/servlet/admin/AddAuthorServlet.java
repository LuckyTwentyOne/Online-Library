package ua.nure.butov.summaryTask4.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.form.AuthorForm;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = { "/admin/addAuthor" })
public class AddAuthorServlet extends AbstractServlet {
	private static final long serialVersionUID = 1087524531290835835L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forwardToPage("admin/addAuthor.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AuthorForm form = createForm(AuthorForm.class, req);
		try {
			getAdminService().checkDataAtAuthorForm(form);
			getAdminService().createAuthor(form);
			req.getSession().setAttribute("createdAuthorMessage", "");
			resp.sendRedirect("/SummaryTask4/admin/home/authors");
		} catch (ValidationException e) {
			req.setAttribute("form", form);
			req.setAttribute("addAuthorErrorMessage", getI18nService().getMessage(e.getMessage(),  getSessionLocale(req)));
			forwardToPage("admin/addAuthor.jsp", req, resp);
		}
	}
}
