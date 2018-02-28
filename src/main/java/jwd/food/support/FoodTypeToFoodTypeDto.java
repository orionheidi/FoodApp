package jwd.food.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.food.model.FoodType;
import jwd.food.web.dto.FoodTypeDTO;

@Component
public class FoodTypeToFoodTypeDto implements Converter<FoodType, FoodTypeDTO>{

	@Override
	public FoodTypeDTO convert(FoodType type) {
		FoodTypeDTO dto = new FoodTypeDTO(type.getId(), type.getName());
		return dto;
	}
	
	public List<FoodTypeDTO> convert(List<FoodType> types){
		List<FoodTypeDTO> dtos = new ArrayList<>();
		
		for(FoodType f : types)
			dtos.add(convert(f));
		
		return dtos;
	}

}
