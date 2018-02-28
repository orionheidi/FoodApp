package jwd.food.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.food.model.Food;
import jwd.food.repository.FoodRepository;
import jwd.food.service.FoodService;

@Service
@Transactional
public class JpaFoodService implements FoodService{

	@Autowired
	FoodRepository foodRepository;
	
	@Override
	public Page<Food> findAll(int page) {
		return foodRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Food findOne(Long id) {
		return foodRepository.findOne(id);
	}

	@Override
	public Food save(Food food) {
		food.setDate(new Date());
		return foodRepository.save(food);
	}

	@Override
	public void delete(Long id) {
		foodRepository.delete(id);
	}

	@Override
	public Page<Food> filterByFoodName(String name, int page) {
		return foodRepository.filterByFoodName(name, new PageRequest(page, 10));
	}

	@Override
	public Page<Food> filterByTypeNameOrDescription(String input, int page) {
		return foodRepository.filterByTypeNameOrDescription(input, new PageRequest(page, 10));
	}
	
}
