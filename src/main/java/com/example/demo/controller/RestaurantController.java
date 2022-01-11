package com.example.demo.controller;

import com.example.demo.model.Restaurant;
import com.example.demo.service.extend.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll(){
      return new ResponseEntity<>(restaurantService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id){
        return new ResponseEntity<>(restaurantService.findById(id).get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurant> save(@RequestBody Restaurant restaurant){
        return new ResponseEntity<>(restaurantService.save(restaurant),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Restaurant> update( @RequestBody Restaurant restaurant){
        return new ResponseEntity<>(restaurantService.save(restaurant),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        restaurantService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
