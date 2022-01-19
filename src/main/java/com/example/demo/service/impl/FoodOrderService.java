package com.example.demo.service.impl;

import com.example.demo.model.FoodOrder;
import com.example.demo.repository.IFoodOrderRepository;
import com.example.demo.service.extend.IFoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FoodOrderService implements IFoodOrderService {


    @Autowired
    private IFoodOrderRepository foodOrderRepository;

    @Override
    public List<FoodOrder> findAll() {
        return foodOrderRepository.findAll();
    }

    @Override
    public Optional<FoodOrder> findById(Long id) {
        return foodOrderRepository.findById(id);
    }

    @Override
    public FoodOrder save(FoodOrder foodOrder) {
        return foodOrderRepository.save(foodOrder);
    }

    @Override
    public void remove(Long id) {
        foodOrderRepository.deleteById(id);
    }


    @Override
    public List<FoodOrder> findAllByUser_Id(Long id) {
        return foodOrderRepository.findAllByUserId(id);
    }

}
