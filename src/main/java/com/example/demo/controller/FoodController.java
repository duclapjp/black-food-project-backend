package com.example.demo.controller;

import com.example.demo.dto.response.ResponMessage;
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
        return new ResponseEntity<>(foodService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Food> foodOptional = foodService.findById(id);
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("Không tìm thấy"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Food> save(@RequestBody Food food) {
        return new ResponseEntity<>(foodService.save(food), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Food food) {
        Optional<Food> foodOptional = foodService.findById(food.getId());
        if (!foodOptional.isPresent()) {
          return new ResponseEntity<>(new ResponMessage("Không tìm thấy"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodService.save(food), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Food> foodOptional = foodService.findById(id);
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("Không tìm thấy"),HttpStatus.NOT_FOUND);
        }
        foodService.remove(id);
        return new ResponseEntity<>(new ResponMessage("Đã Xóa"),HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<Food>> showAllByRestaurantId(@PathVariable Long restaurantId) {
        List<Food> restaurantFoodList = foodService.findAllFoodByrestaurantId(restaurantId);
        if (restaurantFoodList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foodService.findAllFoodByrestaurantId(restaurantId), HttpStatus.OK);
    }

@DeleteMapping("/{restaurantId}/{foodId}")
    public ResponseEntity<?> deleteFoodByRestaurantId(@PathVariable Long restaurantId, @PathVariable Long foodId) {
    Optional<Food> foodOptional = foodService.findById(foodId);
    if (!foodOptional.isPresent()) {
        return new ResponseEntity<>(new ResponMessage("Không tìm thấy"),HttpStatus.NOT_FOUND);
    }
    foodService.deleteFoodByRestaurantId(restaurantId,foodId);
    return new ResponseEntity<>(new ResponMessage("Đã Xóa món"),HttpStatus.OK);
}

}
