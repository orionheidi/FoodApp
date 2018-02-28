package jwd.food.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.food.model.FoodType;
import jwd.food.web.dto.FoodTypeDTO;

@Component
public class FoodTypeDtoToFoodType implements Converter<FoodTypeDTO, FoodType>{

	@Override
	public FoodType convert(FoodTypeDTO dto) {
		FoodType type = new FoodType(dto.getId(), dto.getName(), null);
		return type;
	}
	
	public List<FoodType> convert(List<FoodTypeDTO> dtos){
		List<FoodType> types = new ArrayList<>();
		
		for(FoodTypeDTO dto : dtos)
			types.add(convert(dto));
		
		
		return types;
	}
	
}
