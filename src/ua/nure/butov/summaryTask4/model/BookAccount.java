package ua.nure.butov.summaryTask4.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import ua.nure.butov.summaryTask4.annotation.Column;

/**
 * Relation entity between account and book.
 * 
 * @author V.Butov
 *
 */
public class BookAccount implements Serializable {
	private static final long serialVersionUID = -4149884532096185958L;

	private long id;
	@Column("id_account")
	private long idAccount;
	@Column("id_book")
	private long idBook;
	@Column("borrow_type")
	private String borrowType;
	@Column("borrow_time")
	private Timestamp borrowTime;
	@Column("return_time")
	private Timestamp returnTime;
	private double fine;
	@Column("account_first_name")
	private String accountFirstName;
	@Column("account_last_name")
	private String accountLastName;
	@Column("book_name")
	private String bookName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(long idAccount) {
		this.idAccount = idAccount;
	}

	public long getIdBook() {
		return idBook;
	}

	public void setIdBook(long idBook) {
		this.idBook = idBook;
	}

	public String getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}

	public Timestamp getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Timestamp borrowTime) {
		this.borrowTime = borrowTime;
	}

	public Timestamp getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Timestamp returnTime) {
		this.returnTime = returnTime;
	}

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public String getAccountFirstName() {
		return accountFirstName;
	}

	public void setAccountFirstName(String accountFirstName) {
		this.accountFirstName = accountFirstName;
	}

	public String getAccountLastName() {
		return accountLastName;
	}

	public void setAccountLastName(String accountLastName) {
		this.accountLastName = accountLastName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getFormatedBorrowTime() {
		SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String result = "";
		if (borrowTime != null) {
			Date date = new Date(borrowTime.getTime());
			result = dt.format(date);
		}
		return result;
	}
	
	public String getFormatedReturnTime() {
		SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String result = "";
		if (returnTime != null) {
			Date date = new Date(returnTime.getTime());
			result = dt.format(date);
		}
		return result;
	}

}
