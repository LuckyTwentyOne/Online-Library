package ua.nure.butov.summaryTask4.servlet.librarian;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.model.BookAccount;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = "/librarian/orders")
public class OrdersServlet extends AbstractServlet {
	private static final long serialVersionUID = 2764503530300619864L;


	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long unprocessedOrders = getLibrarianService().countUnprocessedOrders();
		getLibrarianService().countFineForAllDebtors();
		List<BookAccount>ordersList = getLibrarianService().findAllOrders();
		req.setAttribute("ordersList", ordersList);
		req.setAttribute("unprocessedOrders", unprocessedOrders);
		forwardToPage("librarian/orders.jsp", req, resp);
		
	}
}
