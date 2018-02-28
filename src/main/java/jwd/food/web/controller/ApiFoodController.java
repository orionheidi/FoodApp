package jwd.food.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.food.model.Food;
import jwd.food.service.FoodService;
import jwd.food.support.FoodDtoToFood;
import jwd.food.support.FoodToFoodDto;
import jwd.food.web.dto.FoodDTO;

@RestController
@RequestMapping(value="/api/foods")
public class ApiFoodController {
	
	@Autowired
	FoodService foodService;
	
	@Autowired
	FoodDtoToFood foodDtoToFood;
	
	@Autowired
	FoodToFoodDto foodToFoodDto;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<Food>> getAll(
			@RequestParam(value="page", defaultValue="0") int page){
		
		Page<Food> foods = foodService.findAll(page);
		
		if(foods == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			HttpHeaders headers = new HttpHeaders();
			headers.add("totalPages", foods.getTotalPages() + "");
			return new ResponseEntity<List<Food>>(foods.getContent(), headers, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	ResponseEntity<FoodDTO> getOne(@PathVariable Long id){
		
		Food food = foodService.findOne(id);
		
		if(food == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<FoodDTO>(foodToFoodDto.convert(food), HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/filterByFood")
	ResponseEntity<List<FoodDTO>> filterByFoodName(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="name", defaultValue="") String name){
		
		Page<Food> foods = foodService.filterByFoodName(name, page);
		
		if(foods == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			HttpHeaders headers = new HttpHeaders();
			headers.add("totalPages", foods.getTotalPages() + "");
			return new ResponseEntity<List<FoodDTO>>(foodToFoodDto.convert(foods.getContent()), headers, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/filterByType")
	ResponseEntity<List<FoodDTO>> filterByTypeNameOrDescription(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="input", defaultValue="") String input){
		
		Page<Food> foods = foodService.filterByTypeNameOrDescription(input, page);
		
		if(foods == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			HttpHeaders headers = new HttpHeaders();
			headers.add("totalPages", foods.getTotalPages() + "");
			return new ResponseEntity<List<FoodDTO>>(foodToFoodDto.convert(foods.getContent()), headers, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	ResponseEntity<FoodDTO> add(@RequestBody FoodDTO dto){
				
		if(dto.getType().getId() == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Food convertedFood =  foodDtoToFood.convert(dto);
		
		if(convertedFood == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Food retVal = foodService.save(convertedFood);
		
		if(retVal == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<FoodDTO>(foodToFoodDto.convert(retVal), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	ResponseEntity<FoodDTO> edit(
			@RequestBody FoodDTO dto,
			@PathVariable(value="id") Long id){
		
		if(!dto.getId().equals(id))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Food food = foodService.save(foodDtoToFood.convert(dto));
		
		if(food == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<FoodDTO>(foodToFoodDto.convert(food), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	ResponseEntity<HttpStatus> deleteMovie(@PathVariable Long id){
		
		foodService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
