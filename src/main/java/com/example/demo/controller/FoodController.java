package com.example.demo.controller;

import com.example.demo.model.Food;
import com.example.demo.service.extend.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/foods")
public class FoodController {


    @Autowired
    private IFoodService foodService;

    @GetMapping
    public ResponseEntity<List<Food>> findAll() {
        List<Food> foodList = foodService.findAll();
        if (foodList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> findById(@PathVariable Long id) {
        Optional<Food> foodOptional = foodService.findById(id);
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Food> save(@RequestBody Food food) {
        return new ResponseEntity<>(foodService.save(food), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Food> update(@RequestBody Food food) {
        Optional<Food> foodOptional = foodService.findById(food.getId());
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodService.save(food), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Food> foodOptional = foodService.findById(id);
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        foodService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
