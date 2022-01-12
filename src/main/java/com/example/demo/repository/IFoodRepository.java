package com.example.demo.repository;

import com.example.demo.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFoodRepository extends JpaRepository<Food, Long> {
//    @Query(value = "select * from food", nativeQuery = true)
//    List<Food> findAll();
@Query(value = "select f. ,r.name as restaurantName from food join restaurant r on f.restaurant_id = r.id where f.restaurant_id = :id")
    List<Food> findAllByRestaurantId(Long Id);

    void deleteFoodByRestaurantId(Long restaurantId, Long foodId);
}
