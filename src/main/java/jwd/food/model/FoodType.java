package jwd.food.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FoodType {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	
	public FoodType() {
		super();
	}
	
	public FoodType(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public FoodType(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
