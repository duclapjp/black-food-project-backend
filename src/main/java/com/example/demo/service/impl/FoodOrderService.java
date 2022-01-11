package com.example.demo.service.impl;

import com.example.demo.model.FoodOrder;
import com.example.demo.repository.IFoodOrderRepository;
import com.example.demo.service.extend.IFoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderService implements IFoodOrderService {

    @Autowired
    private IFoodOrderRepository foodOrderRepository;

    @Override
    public List<FoodOrder> findAll() {
        return null;
    }

    @Override
    public Optional<FoodOrder> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public FoodOrder save(FoodOrder foodOrder) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
