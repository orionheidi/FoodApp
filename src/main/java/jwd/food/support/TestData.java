package jwd.food.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.food.model.Food;
import jwd.food.model.FoodType;
import jwd.food.service.FoodService;
import jwd.food.service.FoodTypeService;

@Component
public class TestData {
	@Autowired
	FoodService foodService;
	
	@Autowired
	FoodTypeService foodTypeService;
	
	@PostConstruct
	public void init(){
		for(int i=1; i<=5; i++){
			FoodType type = new FoodType();
			type.setName("FoodType" + i);
			type.setDescription("Description" + i);
			List<Food> foods = new ArrayList<>();
			
			for(int j=1; j<=5; j++){
				int random = (int)(Math.random() * 10);
				Food food = new Food("FoodName" + random, 200 + (random * 10), new Date(), type);
				foods.add(food);
			}

			foodTypeService.save(type);
			
			for(Food f : foods){
				foodService.save(f);
			}
		}
	}
}
