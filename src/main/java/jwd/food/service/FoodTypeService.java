package jwd.food.service;

import java.util.List;

import jwd.food.model.FoodType;

public interface FoodTypeService {
	public List<FoodType> findAll();
	public FoodType findOne(Long id);
	public FoodType save(FoodType foodType);
}
