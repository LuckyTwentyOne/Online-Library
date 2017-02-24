package ua.nure.butov.summaryTask4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.form.LoginForm;
import ua.nure.butov.summaryTask4.model.Account;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends AbstractServlet {
	private static final long serialVersionUID = 2764503530300619864L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginForm form = createForm(LoginForm.class, req);
		try {
			Account account = getCommonService().login(form);
			req.getSession().setAttribute(Constants.CURRENT_ACCOUNT, account);
			getCommonService().redirectDependsOnRole(account, resp);
		} catch (ValidationException e) {
			req.setAttribute("loginForm", form);
			req.setAttribute("loginErrorMessage", getI18nService().getMessage(e.getMessage(), getSessionLocale(req)));
			forwardToPage("login.jsp", req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute(Constants.CURRENT_ACCOUNT) != null) {
			Account account = (Account) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
			getCommonService().redirectDependsOnRole(account, resp);
		} else {
			forwardToPage("login.jsp", req, resp);
			req.getSession().setAttribute("successRegistrationMessage", null);
		}
	}
}
