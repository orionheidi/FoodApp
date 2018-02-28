package jwd.food.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.food.model.Food;
import jwd.food.model.FoodType;
import jwd.food.service.FoodTypeService;
import jwd.food.web.dto.FoodDTO;

@Component
public class FoodDtoToFood implements Converter<FoodDTO, Food>{

	@Autowired
	FoodTypeService foodTypeService;
	
	@Override
	public Food convert(FoodDTO dto) {
		Food food = new Food();
		food.setId(dto.getId());
		food.setName(dto.getName());
		food.setPrice(dto.getPrice());
		
		if(dto.getType() != null){
			FoodType type = foodTypeService.findOne(dto.getType().getId());
			
			if(type != null){
				food.setType(type);
				return food;
			}else
				return null;
		}else
			return null;
		
	}
	
	public List<Food> convert(List<FoodDTO> dtos){
		List<Food> foods = new ArrayList<>();
		
		for(FoodDTO dto : dtos)
			foods.add(convert(dto));
		
		return foods;
	}

}
