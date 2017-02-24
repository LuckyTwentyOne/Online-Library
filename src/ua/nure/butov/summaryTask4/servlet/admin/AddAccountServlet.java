package ua.nure.butov.summaryTask4.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.form.AddAccountForm;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = { "/admin/addAccount" })
public class AddAccountServlet extends AbstractServlet {
	private static final long serialVersionUID = 1087524531290835835L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forwardToPage("admin/addAccount.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AddAccountForm form = createForm(AddAccountForm.class, req);
		try {
			getAdminService().checkAccountRegistrationData(form);
			getAdminService().addAccount(form);
			req.getSession().setAttribute("addedAccount", form.getLogin());
			resp.sendRedirect("/SummaryTask4/admin/home");
		} catch (ValidationException e) {
			req.setAttribute("form", form);
			req.setAttribute("addAccountErrorMessage", getI18nService().getMessage(e.getMessage(),  getSessionLocale(req)));
			forwardToPage("admin/addAccount.jsp", req, resp);
		}
	}
}
