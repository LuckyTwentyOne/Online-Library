package ua.nure.butov.summaryTask4.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = { "/admin/deleteAccount" })
public class DeleteAccountServlet extends AbstractServlet {
	private static final long serialVersionUID = 1087524531290835835L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long idAccount = Long.valueOf(req.getParameter("id"));
		Account account = getAdminService().findAccountById(idAccount);
		getAdminService().deleteAccount(idAccount);
		req.getSession().setAttribute("deletedAccount", account.getLogin());
		resp.sendRedirect("/SummaryTask4/admin/home");
	}
}
