package ua.nure.butov.summaryTask4.model;

import java.io.Serializable;
import java.sql.Timestamp;

import ua.nure.butov.summaryTask4.annotation.Column;

/**
 * Account entity.
 * 
 * @author V.Butov
 *
 */
public class Account implements Serializable {
	private static final long serialVersionUID = 151580772550240219L;

	private Long id;
	private String login;
	private String password;
	private String email;
	@Column("first_name")
	private String firstName;
	@Column("last_name")
	private String lastName;
	@Column("id_role")
	private Long idRole;
	private Timestamp created;
	private Timestamp updated;
	private Boolean active;
	@Column("role_name")
	private String roleName;

	public Account() {
	}

	public Account(Long id, String login, String password, String email, String firstName, String lastName, Long idRole,
			Timestamp created, Timestamp updated, Boolean active) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idRole = idRole;
		this.created = created;
		this.updated = updated;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", idRole=" + idRole + ", created=" + created + ", updated="
				+ updated + ", active=" + active + ", roleName=" + roleName + "]";
	}
}
