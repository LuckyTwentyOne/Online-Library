package ua.nure.butov.summaryTask4.model;

import ua.nure.butov.summaryTask4.annotation.IgnorableOnInput;

/**
 * Role entity.
 * 
 * @author V.Butov
 *
 */
public class Role {
	@IgnorableOnInput
	private Long id;
	private String name;
	
	public Role() {
	}
	public Role(String name) {
		super();
		this.name = name;
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
	
	
}
