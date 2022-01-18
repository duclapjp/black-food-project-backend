package com.example.demo.controller;

import com.example.demo.model.Food;
import com.example.demo.model.Message;
import com.example.demo.service.extend.IFoodOrderService;
import com.example.demo.service.extend.IFoodService;
import com.example.demo.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/foods")
public class FoodController {


    @Autowired
    private IFoodService foodService;

    @GetMapping
    public ResponseEntity<List<Food>> findAll() {

        return new ResponseEntity<>(foodService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> findById(@PathVariable Long id) {
        return new ResponseEntity<>(foodService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Food> save(@RequestBody Food food) {
        return new ResponseEntity<>(foodService.save(food), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Food> update(@RequestBody Food food) {
        return new ResponseEntity<>(foodService.save(food), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        foodService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Food>> findByRestaurantId(@PathVariable Long id) {
        return new ResponseEntity<>(foodService.findAllByRestaurantId(id), HttpStatus.OK);
    }

    @GetMapping("/search/{id}/{name}")
    public ResponseEntity<List<Food>> findByName(@PathVariable Long id, @PathVariable String name) {
        return new ResponseEntity<>(foodService.findAllByRestaurant_IdAndNameContaining(id, name), HttpStatus.OK);
    }
}
