package ua.nure.butov.summaryTask4.serviceImpl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.annotation.Transactional;
import ua.nure.butov.summaryTask4.exception.ValidationException;
import ua.nure.butov.summaryTask4.form.LoginForm;
import ua.nure.butov.summaryTask4.form.RegistrationForm;
import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.model.AccountRegistration;
import ua.nure.butov.summaryTask4.model.SocialAccount;
import ua.nure.butov.summaryTask4.repository.AccountRegistrationRepository;
import ua.nure.butov.summaryTask4.repository.AccountRepository;
import ua.nure.butov.summaryTask4.service.CommonService;
import ua.nure.butov.summaryTask4.util.CaptchaConfig;

/**
 * Provides implementation of {@link CommonService} interface.
 * 
 * @author V.Butov
 *
 */
class CommonServiceImpl implements CommonService {
	private static final Logger LOGGER = Logger.getLogger(CommonServiceImpl.class);
	private final AccountRepository accountRepository;
	private final AccountRegistrationRepository accountRegistrationRepository;

	CommonServiceImpl(AccountRepository accountRepository,
			AccountRegistrationRepository accountRegistrationRepository) {
		this.accountRepository = accountRepository;
		this.accountRegistrationRepository = accountRegistrationRepository;
	}

	@Transactional
	@Override
	public Account login(LoginForm form) throws ValidationException {
		Account account = accountRepository.findByLogin(form.getLogin());
		if (account == null) {
			throw new ValidationException("validationError.login-login");
		}
		if (!account.getPassword().equals(form.getPassword())) {
			throw new ValidationException("validationError.login-password");
		}
		if (!account.getActive()) {
			throw new ValidationException("validationError.login-notActive");
		}
		return account;
	}

	@Transactional
	@Override
	public void checkRegistrationData(RegistrationForm form) throws ValidationException {
		checkInputLenght(form, 3, 8);
		Account account = accountRepository.findByLogin(form.getLogin());
		if (account != null) {
			throw new ValidationException("registrationError.loginExist");
		}
		account = accountRepository.findByEmail(form.getEmail());
		if (account != null) {
			throw new ValidationException("registrationError.emailExist");
		}
		if (!form.getPassword().equals(form.getConfirmPassword())) {
			throw new ValidationException("registrationError.notEqualPasswords");
		}
	}

	private void checkInputLenght(RegistrationForm form, int minInputLenght, int minPasswordLenght)
			throws ValidationException {
		Field[] fields = form.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (((String) field.get(form)).length() < minInputLenght) {
					throw new ValidationException("registrationError.lengthFieldError");
				}
				if (field.getName().equals("password") && ((String) field.get(form)).length() < minPasswordLenght) {
					throw new ValidationException("registrationError.passwordLengthError");
				}
			} catch (IllegalArgumentException e) {
				LOGGER.error("Error into checkInputLenght method", e);
			} catch (IllegalAccessException e) {
				LOGGER.error("Error into checkInputLenght method", e);
			}
		}
	}

	@Override
	public void redirectDependsOnRole(Account account, HttpServletResponse resp) throws IOException {
		if (account.getIdRole() == 1) {
			resp.sendRedirect("admin/home");
		} else if (account.getIdRole() == 2) {
			resp.sendRedirect("librarian/home");
		} else if (account.getIdRole() == 3) {
			resp.sendRedirect("reader/home");
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void registerAccount(RegistrationForm form, String hash) {
		Account newAccount = accountRepository.register(form);
		accountRegistrationRepository.addActivateHashToAccount(newAccount.getId(), hash);
	}

	@Override
	public int countNeededPages(int numberOfRecords, int recordsPerPage) {
		return (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
	}

	@Transactional(readOnly = false)
	@Override
	public Account activateAccountByCode(String code) {
		AccountRegistration record = accountRegistrationRepository.findByCode(code);
		if (record != null) {
			accountRegistrationRepository.deleteRecord(record.getIdAccount());
			accountRepository.activateAccount(record.getIdAccount());
			return accountRepository.findById(record.getIdAccount());
		} else {
			return null;
		}

	}

	@Override
	public void verifyCaptcha(String gRecaptchaResponse) throws ValidationException {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			throw new ValidationException("validationException.captchaNotFilled");
		}
		try {
			URL obj = new URL(CaptchaConfig.getUrl());
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", CaptchaConfig.getUserAgent());
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			String postParams = "secret=" + CaptchaConfig.getSecretKey() + "&response=" + gRecaptchaResponse;
			con.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.writeBytes(postParams);
				try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
					String inputLine;
					StringBuffer response = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					try (JsonReader jsonReader = Json.createReader(new StringReader(response.toString()))) {
						JsonObject jsonObject = jsonReader.readObject();
						if (!jsonObject.getBoolean("success")) {
							throw new ValidationException("validationException.captchaFailed");
						}
					}
				}
			}
		} catch (Exception e) {
			if (e instanceof ValidationException) {
				throw (ValidationException) e;
			}
			LOGGER.error(e.getMessage(), e);

		}
	}

	@Transactional(readOnly = false)
	@Override
	public Account authentificate(SocialAccount socialAccount) {
		Account account = accountRepository.findByEmail(socialAccount.getEmail());
		if (account == null) {
			account = accountRepository.registerBySocialAccount(socialAccount);
		}
		return account;
	}

	@Override
	public String hash(String sourceString) {
        return DigestUtils.sha256Hex(sourceString+"alto");
    }
}
