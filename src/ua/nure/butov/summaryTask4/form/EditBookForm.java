package ua.nure.butov.summaryTask4.form;

import java.io.Serializable;

import ua.nure.butov.summaryTask4.annotation.Array;
import ua.nure.butov.summaryTask4.annotation.IgnorableOnInput;

/**
 * Represents 'edit book' form.
 * 
 * @author V.Butov
 *
 */
public class EditBookForm implements Serializable {
	private static final long serialVersionUID = 162370239075181887L;
	@IgnorableOnInput
	private long id;
	private String name;
	private String publisher;
	private int imprintYear;
	private String alternativeName;
	@IgnorableOnInput
	@Array
	private Long[] author;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public Long[] getAuthor() {
		return author;
	}
	public void setAuthor(Long[] author) {
		this.author = author;
	}
}
