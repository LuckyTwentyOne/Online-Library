package ua.nure.butov.summaryTask4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.model.Account;

@WebServlet(urlPatterns = { "/activation" })
public class ActivationAccountServlet extends AbstractServlet {
	private static final long serialVersionUID = 6071300428245450508L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		Account currentAccount = getCommonService().activateAccountByCode(code);
		if (currentAccount != null) {
			req.getSession().setAttribute(Constants.CURRENT_ACCOUNT, currentAccount);
			getCommonService().redirectDependsOnRole(currentAccount, resp);
		} else {
			resp.sendRedirect("login");
		}
	}
}
