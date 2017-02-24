package ua.nure.butov.summaryTask4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.form.RegistrationForm;

@WebServlet(urlPatterns = { "/registration" })
public class RegistrationServlet extends AbstractServlet {
	private static final long serialVersionUID = 1768120242463135240L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forwardToPage("registration.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RegistrationForm form = createForm(RegistrationForm.class, req);
		String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
		try {	
			getCommonService().checkRegistrationData(form);
			getCommonService().verifyCaptcha(gRecaptchaResponse);
			String hash = getCommonService().hash(form.getLogin() + form.getEmail());
			getCommonService().registerAccount(form, hash);
			req.getSession().setAttribute("successRegistrationMessage",
					getI18nService().getMessage("login.successRegistrationMessage",  getSessionLocale(req)));
			getMailService().send(getApplicationProperty("mail.message.regSuccessSubject"),
					getApplicationProperty("mail.message.regSuccessText") + hash,
					getApplicationProperty("mail.address"), form.getEmail());
			resp.sendRedirect("login");
		} catch (ValidationException e) {
			req.setAttribute("form", form);
			req.setAttribute("registrationErrorMessage", getI18nService().getMessage(e.getMessage(),  getSessionLocale(req)));
			forwardToPage("registration.jsp", req, resp);
		}
	}

}
