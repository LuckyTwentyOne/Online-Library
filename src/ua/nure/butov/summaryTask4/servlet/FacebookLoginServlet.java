package ua.nure.butov.summaryTask4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.model.Account;

@WebServlet(urlPatterns = "/fbLogin")
public class FacebookLoginServlet extends AbstractServlet {
	private static final long serialVersionUID = 2764503530300619864L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute(Constants.CURRENT_ACCOUNT) != null) {
			Account account = (Account) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
			getCommonService().redirectDependsOnRole(account, resp);
		} else {
			resp.sendRedirect(getSocialService().getAuthorizeUrl());
		}
	}
}
