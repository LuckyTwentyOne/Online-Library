package ua.nure.butov.summaryTask4.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.form.EditAccountForm;
import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = { "/admin/editAccount" })
public class EditAccountServlet extends AbstractServlet {
	private static final long serialVersionUID = 1087524531290835835L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long idAccount = Long.valueOf(req.getParameter("id"));
		Account account = getAdminService().findAccountById(idAccount);
		req.setAttribute("account", account);
		forwardToPage("admin/editAccount.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EditAccountForm form = createForm(EditAccountForm.class, req);
		try {
			getAdminService().checkAccountEditionData(form, 3);
			getAdminService().updateAccount(form);
			req.getSession().setAttribute("successfullyUpdatedAccount", form.getLogin());
			resp.sendRedirect("/SummaryTask4/admin/home");
		} catch (ValidationException e) {
			Account account = getAdminService().findAccountById(form.getId());
			req.setAttribute("account", account);
			req.setAttribute("editAccountErrorMessage", getI18nService().getMessage(e.getMessage(),  getSessionLocale(req)));
			forwardToPage("admin/editAccount.jsp", req, resp);
		}
	}
}
