package ua.nure.butov.summaryTask4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.model.SocialAccount;

@WebServlet("/from-social")
public class FromSocialServlet extends AbstractServlet {
	private static final long serialVersionUID = -8146770694377066438L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		if (code != null) {
			SocialAccount socialAccount = getSocialService().getSocialAccount(code);
			Account currentAccount = getCommonService().authentificate(socialAccount);
			req.getSession().setAttribute(Constants.CURRENT_ACCOUNT, currentAccount);
			getCommonService().redirectDependsOnRole(currentAccount, resp);
		} else {
			LOGGER.warn("Parameter code not found");
			resp.sendRedirect("login");
		}
	}
}
