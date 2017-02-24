package ua.nure.butov.summaryTask4.servlet.librarian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = "/librarian/closeOrder")
public class CloseOrderServlet extends AbstractServlet {
	private static final long serialVersionUID = 2764503530300619864L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/SummaryTask4/librarian/orders");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long idOrder = Long.valueOf(req.getParameter("idOrder"));
		getLibrarianService().closeOrder(idOrder);
		resp.sendRedirect("/SummaryTask4/librarian/orders");
	}
}
