package ua.nure.butov.summaryTask4.model;

import java.io.Serializable;

/**
 * Relation entity between author and book.
 * 
 * @author V.Butov
 *
 */
public class BookAuthor implements Serializable {
	private static final long serialVersionUID = 8937086048330562L;
	private long id;
	private long idBook;
	private long idAuthor;
	
	public BookAuthor() {
	}

	public BookAuthor(long idBook, long idAuthor) {
		this.idBook = idBook;
		this.idAuthor = idAuthor;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public long getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}
}
