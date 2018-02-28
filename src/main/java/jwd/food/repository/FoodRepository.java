package jwd.food.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jwd.food.model.Food;

@Repository
public interface FoodRepository extends PagingAndSortingRepository<Food, Long>{
	@Query(value="select x from Food x where x.name like %?1%")
	public Page<Food> filterByFoodName(String name, Pageable pageable);
	
	@Query(value="select x from Food x where x.type.name like %?1% or x.type.description like %?1%")
	public Page<Food> filterByTypeNameOrDescription(String input, Pageable pageable);
}
