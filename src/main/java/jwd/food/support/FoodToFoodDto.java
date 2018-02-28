package jwd.food.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.food.model.Food;
import jwd.food.web.dto.FoodDTO;

@Component
public class FoodToFoodDto implements Converter<Food, FoodDTO>{

	@Autowired
	FoodTypeToFoodTypeDto typeToTypeDto;
	
	@Override
	public FoodDTO convert(Food food) {
		FoodDTO dto = new FoodDTO(food.getId(), food.getName(), food.getPrice(), typeToTypeDto.convert(food.getType()));
		return dto;
	}
	
	public List<FoodDTO> convert(List<Food> foods){
		List<FoodDTO> dtos = new ArrayList<>();
		
		for(Food f : foods)
			dtos.add(convert(f));
		
		return dtos;
	}

}
