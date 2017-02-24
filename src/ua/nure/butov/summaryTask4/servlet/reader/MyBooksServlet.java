package ua.nure.butov.summaryTask4.servlet.reader;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.model.BookAccount;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = "/reader/myBooks")
public class MyBooksServlet extends AbstractServlet {
	private static final long serialVersionUID = 2764503530300619864L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Account account = (Account) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
		List<BookAccount>ordersList = getReaderService().getReaderOrders(account.getId());
		req.setAttribute("ordersList", ordersList);
			forwardToPage("reader/myBooks.jsp", req, resp);	
	}
}
