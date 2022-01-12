package com.example.demo.repository;
import com.example.demo.model.FoodOrder;
import com.example.demo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {
}
