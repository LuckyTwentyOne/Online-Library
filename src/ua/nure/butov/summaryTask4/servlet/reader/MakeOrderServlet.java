package ua.nure.butov.summaryTask4.servlet.reader;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.exception.UsersException;
import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = "/makeOrder")
public class MakeOrderServlet extends AbstractServlet {
	private static final long serialVersionUID = 2764503530300619864L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("CURRENT_ACCOUNT") == null) {
			req.getSession().setAttribute("errorMessage",
					getI18nService().getMessage("readerHome.loginErrorMessage",  getSessionLocale(req)));
			resp.sendRedirect("main");
		} else {
			long idBook = Long.valueOf(req.getParameter("idBook"));
			Account account = (Account) req.getSession().getAttribute("CURRENT_ACCOUNT");
			try {
				getReaderService().makeOrder(account.getId(), idBook);
				resp.sendRedirect("/SummaryTask4/reader/myBooks");
			} catch (UsersException e) {
				req.getSession().setAttribute("errorMessage", getI18nService().getMessage(e.getMessage(),  getSessionLocale(req)));
				resp.sendRedirect("/SummaryTask4/reader/home");
			}
		}
	}
}
