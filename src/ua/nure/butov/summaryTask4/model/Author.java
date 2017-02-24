package ua.nure.butov.summaryTask4.model;

import java.io.Serializable;
import java.util.List;

import ua.nure.butov.summaryTask4.annotation.Column;
import ua.nure.butov.summaryTask4.annotation.IgnorableOnDefaultSetHandler;

/**
 * Author entity.
 * 
 * @author V.Butov
 *
 */
public class Author implements Serializable{
	private static final long serialVersionUID = 6152325050083320106L;
	
	private long id;
	@Column("first_name")
	private String firstName;
	@Column("last_name")
	private String lastName;
	@Column("alternative_last_name")
	private String alternativeLastName;
	@IgnorableOnDefaultSetHandler
	private List<Book>books;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getAlternativeLastName() {
		return alternativeLastName;
	}
	public void setAlternativeLastName(String alternativeLastName) {
		this.alternativeLastName = alternativeLastName;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
