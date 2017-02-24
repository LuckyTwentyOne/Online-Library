package ua.nure.butov.summaryTask4.form;

import java.io.Serializable;

/**
 * Represents 'edit account' form.
 * 
 * @author V.Butov
 *
 */
public class EditAccountForm implements Serializable {
	private static final long serialVersionUID = -1065316498699349042L;

	private long id;
	private String login;
	private String firstName;
	private String lastName;
	private long role;
	private boolean active;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
