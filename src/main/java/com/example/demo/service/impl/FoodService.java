package com.example.demo.service.impl;

import com.example.demo.model.Food;
import com.example.demo.repository.IFoodRepository;
import com.example.demo.service.extend.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService implements IFoodService {

    @Autowired
    private IFoodRepository foodRepository;

    @Override
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public Optional<Food> findById(Long id) {
        return foodRepository.findById(id);
    }

    @Override
    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public void remove(Long id) {
        foodRepository.deleteById(id);
    }


    @Override
    public List<Food> findAllByRestaurantId(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }

    @Override
    public List<Food> findAllByRestaurant_IdAndNameContaining(Long id, String name) {
        return foodRepository.findAllByRestaurantIdAndNameContaining(id,name);
    }


}
