package com.example.demo.service.extend;

import com.example.demo.model.Food;
import com.example.demo.service.IGeneralService;

import java.util.List;

public interface IFoodService extends IGeneralService<Food> {
    List<Food> showAllFoodByRestaurantId(Long restaurantId);
}
