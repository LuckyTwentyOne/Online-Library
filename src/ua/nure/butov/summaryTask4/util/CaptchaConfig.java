package ua.nure.butov.summaryTask4.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.exception.FileException;

public final class CaptchaConfig {
	
	private CaptchaConfig() {
	}

	private static final Logger LOGGER = Logger.getLogger(CaptchaConfig.class);
	private static final String FILE = "/captcha.properties";
	private static final String URL;
	private static final String SECRET_KEY;
	private static final String USER_AGENT;

	static {
		try (InputStream resource = CaptchaConfig.class.getResourceAsStream(FILE)) {
			Properties prop = new Properties();
			prop.load(resource);
			URL = prop.getProperty("captcha.url");
			SECRET_KEY = prop.getProperty("captcha.secret");
			USER_AGENT = prop.getProperty("captcha.userAgent");
		} catch (IOException e) {
			LOGGER.error("Cannot load file: " + FILE);
			throw new FileException("Cannot load file: '" + FILE + "'", e);
		}
	}

	public static String getUrl() {
		return URL;
	}

	public static String getSecretKey() {
		return SECRET_KEY;
	}

	public static String getUserAgent() {
		return USER_AGENT;
	}
}
