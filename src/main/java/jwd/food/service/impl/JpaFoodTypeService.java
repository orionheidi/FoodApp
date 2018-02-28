package jwd.food.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.food.model.FoodType;
import jwd.food.repository.FoodTypeRepository;
import jwd.food.service.FoodTypeService;

@Service
public class JpaFoodTypeService implements FoodTypeService{

	@Autowired
	FoodTypeRepository foodTypeRepository;
	
	@Override
	public List<FoodType> findAll() {
		return foodTypeRepository.findAll();
	}

	@Override
	public FoodType save(FoodType foodType) {
		return foodTypeRepository.save(foodType);
	}

	@Override
	public FoodType findOne(Long id) {
		return foodTypeRepository.findOne(id);
	}

}
