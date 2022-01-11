package com.example.demo.repository;

import com.example.demo.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodOrderRepository  extends JpaRepository<FoodOrder,Long> {
}
