package com.example.demo.repository;

import com.example.demo.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFoodRepository extends JpaRepository<Food,Long> {
    List<Food> findAllByRestaurantId(Long restaurantId);
    List<Food> findAllByRestaurantIdAndNameContaining(Long id,String name);
}
