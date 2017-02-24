package ua.nure.butov.summaryTask4.model;

import ua.nure.butov.summaryTask4.annotation.Column;

/**
 * Model to get genereted ID into different tables.
 * 
 * @author V.Butov
 *
 */
public class Id {
	@Column("GENERATED_KEY")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
