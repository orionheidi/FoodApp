package jwd.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.food.model.FoodType;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, Long>{

}
