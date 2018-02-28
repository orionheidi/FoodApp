package jwd.food.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Food {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private double price;
	private Date date = new Date();
	@ManyToOne
	private FoodType type;
	
	public Food() {
		super();
	}
	
	public Food(String name, double price, Date date, FoodType type) {
		super();
		this.name = name;
		this.price = price;
		this.date = date;
		this.type = type;
	}
	
	public Food(Long id, String name, double price, Date date, FoodType type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.date = date;
		this.type = type;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public FoodType getType() {
		return type;
	}
	public void setType(FoodType type) {
		this.type = type;
	}
	
}
