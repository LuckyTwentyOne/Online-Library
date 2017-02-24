package ua.nure.butov.summaryTask4.servlet.librarian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.model.BookAccount;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = "/librarian/processOrder")
public class ProcessOrderServlet extends AbstractServlet {
	private static final long serialVersionUID = 2764503530300619864L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long orderId = Long.valueOf(req.getParameter("id"));
		long unprocessedOrders = getLibrarianService().countUnprocessedOrders();
		BookAccount order = getLibrarianService().findOrderById(orderId);
		req.setAttribute("order", order);
		req.setAttribute("unprocessedOrders", unprocessedOrders);
		if (order.getReturnTime() == null) {
			forwardToPage("librarian/newOrder.jsp", req, resp);
		} else {
			forwardToPage("librarian/closeOrder.jsp", req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long orderId = Long.valueOf(req.getParameter("id"));
		BookAccount order = getLibrarianService().findOrderById(orderId);
		req.setAttribute("order", order);
		try {
			getLibrarianService().processNewOrderForm(req);
			resp.sendRedirect("/SummaryTask4/librarian/orders");
		} catch (ValidationException e) {
			req.setAttribute("validationErrorMessage", getI18nService().getMessage(e.getMessage(),  getSessionLocale(req)));
			forwardToPage("librarian/newOrder.jsp", req, resp);
		}
	}
}
