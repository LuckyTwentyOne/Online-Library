package ua.nure.butov.summaryTask4.service;

import ua.nure.butov.summaryTask4.model.SocialAccount;

/**
 * Provides functionality to connect external social account
 * with application.
 * 
 * @author V.Butov
 *
 */
public interface SocialService {
	
	String getAuthorizeUrl();
	
	SocialAccount getSocialAccount(String authToken);
}
