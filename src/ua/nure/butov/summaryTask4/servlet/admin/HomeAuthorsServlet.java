package ua.nure.butov.summaryTask4.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.constants.Constants;
import ua.nure.butov.summaryTask4.model.Author;
import ua.nure.butov.summaryTask4.servlet.AbstractServlet;

@WebServlet(urlPatterns = { "/admin/home/authors" })
public class HomeAuthorsServlet extends AbstractServlet {
	private static final long serialVersionUID = 6071300428245450508L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int limit = Constants.DEFAULT_ROW_LIMIT_FOR_ADMIN;
		if (req.getParameter("page") != null) {
			page = Integer.valueOf(req.getParameter("page"));
		}
		List<Author> authorsList = getAdminService().findAllAuthors(req,limit,(page - 1) * limit);
		int totalRecords = (int) req.getAttribute("foundRecords");
		int neededPages = getCommonService().countNeededPages(totalRecords, limit);
		req.setAttribute("authorsList", authorsList);
		req.setAttribute("neededPages", neededPages);
		req.setAttribute("pageNumber", page);
		forwardToPage("admin/homeAuthors.jsp", req, resp);
		//req.getSession().setAttribute("updatedBookMessage", null);
		//req.getSession().setAttribute("deletedBook", null);
		req.getSession().setAttribute("createdAuthorMessage", null);
	}
}