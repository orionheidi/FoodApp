package jwd.food.service;

import org.springframework.data.domain.Page;

import jwd.food.model.Food;

public interface FoodService {
	public Page<Food> findAll(int page);
	public Page<Food> filterByFoodName(String name, int page);
	public Page<Food> filterByTypeNameOrDescription(String input, int page);
	public Food findOne(Long id);
	public Food save(Food food);
	public void delete(Long id);
}
