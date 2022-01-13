package com.example.demo.repository;

import com.example.demo.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFoodRepository extends JpaRepository<Food,Long> {
    List<Food> findAllByRestaurantId(Long restaurantId);

    Optional<Food> findByName(String foodName);
}
