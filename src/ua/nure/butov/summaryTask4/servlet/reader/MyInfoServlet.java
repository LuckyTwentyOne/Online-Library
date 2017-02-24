package ua.nure.butov.summaryTask4.servlet.reader;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = "/reader/myInfo")
public class MyInfoServlet extends AbstractServlet {
	private static final long serialVersionUID = 2764503530300619864L;


	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			forwardToPage("reader/myInfo.jsp", req, resp);	
	}
}
