package ua.nure.butov.summaryTask4.form;

import ua.nure.butov.summaryTask4.annotation.IgnorableOnInput;

/**
 * Represents 'registration account' form.
 * 
 * @author V.Butov
 *
 */
public class RegistrationForm {

	private String login;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	@IgnorableOnInput
	private String confirmPassword;
	
	public RegistrationForm() {
	}

	public RegistrationForm(String login, String email, String firstName, String lastName, String password,
			String confirmPassword) {
		this.login = login;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
