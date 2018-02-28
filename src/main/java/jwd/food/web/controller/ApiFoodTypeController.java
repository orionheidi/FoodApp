package jwd.food.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.food.model.FoodType;
import jwd.food.service.FoodTypeService;

@RestController
@RequestMapping(value="/api/types")
public class ApiFoodTypeController {
	
	@Autowired
	FoodTypeService typeService;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<FoodType>> getAll(
			@RequestParam(value="page", defaultValue="0") int page){
		
		List<FoodType> types = typeService.findAll();
		
		if(types == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<FoodType>>(types, HttpStatus.OK);
		}
	}
}
