package com.example.demo.service.extend;

import com.example.demo.model.Food;
import com.example.demo.service.IGeneralService;

import java.util.List;

public interface IFoodService extends IGeneralService<Food> {
    List<Food> findAllByRestaurantId(Long restaurantId);
    List<Food> findAllByRestaurant_IdAndNameContaining(Long id,String name);
}
