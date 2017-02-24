package ua.nure.butov.summaryTask4.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import ua.nure.butov.summaryTask4.annotation.Column;
import ua.nure.butov.summaryTask4.annotation.IgnorableOnDefaultSetHandler;

/**
 * Book entity.
 * 
 * @author V.Butov
 *
 */
public class Book implements Serializable {
	private static final long serialVersionUID = -760751518548268458L;

	private Long id;
	private String name;
	private String publisher;
	@Column("imprint_year")
	private int imprintYear;
	@Column("alternative_name")
	private String alternativeName;
	private Timestamp created;
	@IgnorableOnDefaultSetHandler
	private List<Author> authors;
	@IgnorableOnDefaultSetHandler
	private long numberOfCopies;
	@IgnorableOnDefaultSetHandler
	private long freeCopies;
	@Column("total_orders")
	private int totalOrders;
	private double percent;
	
	public Book() {
	}
	
	public Book(Long id, String name, String publisher, int imprintYear, String alternativeName) {
		this.id = id;
		this.name = name;
		this.publisher = publisher;
		this.imprintYear = imprintYear;
		this.alternativeName = alternativeName;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getImprintYear() {
		return imprintYear;
	}

	public void setImprintYear(int imprintYear) {
		this.imprintYear = imprintYear;
	}

	public String getAlternativeName() {
		return alternativeName;
	}

	public void setAlternativeName(String alternativeName) {
		this.alternativeName = alternativeName;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public long getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(long numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	
	public long getFreeCopies() {
		return freeCopies;
	}

	public void setFreeCopies(long freeCopies) {
		this.freeCopies = freeCopies;
	}
	

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	public String getFormatedPercent(){
		return String.format("%.2f", percent);
	}

	public String getAuthorList() {
		StringBuilder result = new StringBuilder();
		if (authors.size()!=0) {
			for (Author author : authors) {
				result.append(author.getFirstName() + " " + author.getLastName() + "; ");
			}
			result.delete(result.length() - 2, result.length() - 1);
		}
		return result.toString();
	}

}
