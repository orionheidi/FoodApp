package jwd.food.web.dto;

public class FoodDTO {
	private Long id;
	private String name;
	private double price;
	private FoodTypeDTO type;
	public FoodDTO() {
		super();
	}
	public FoodDTO(Long id, String name, double price, FoodTypeDTO type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
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
	public FoodTypeDTO getType() {
		return type;
	}
	public void setType(FoodTypeDTO type) {
		this.type = type;
	}
	
	
}
