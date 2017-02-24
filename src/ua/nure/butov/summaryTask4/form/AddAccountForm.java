package ua.nure.butov.summaryTask4.form;

import ua.nure.butov.summaryTask4.annotation.IgnorableOnInput;

/**
 * Represents 'add account' form.
 * 
 * @author V.Butov
 *
 */
public class AddAccountForm {

	private String login;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	@IgnorableOnInput
	private String confirmPassword;
	private Long idRole;
	private Boolean active;

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

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
