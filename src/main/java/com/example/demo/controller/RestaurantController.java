package com.example.demo.controller;

import com.example.demo.model.FoodOrder;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.extend.IRestaurantService;
import com.example.demo.service.extend.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IUserService userService;

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
        Restaurant restaurant1 = restaurantService.save(restaurant);
        User user = userDetailService.getCurrentUser();
        user.setRestaurantId(restaurant1.getId());
        userService.save(user);
        return new ResponseEntity<>(restaurant1,HttpStatus.CREATED);
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
    @GetMapping("/bookingFO/{resId}")
    public ResponseEntity<List<FoodOrder>> bookingFOList(@PathVariable Long resId){
        Restaurant restaurant = restaurantService.findById(resId).get();
        List<FoodOrder> foodOrderListOfRes = restaurant.getFoodOrderList();
        List<FoodOrder>foodOrderBookingListOfRes = new ArrayList<>();
        for (FoodOrder fo: foodOrderListOfRes) {
            if (fo.getGeneralStatus().getId() == 5) {
                foodOrderBookingListOfRes.add(fo);
            }
        }
        return new ResponseEntity<>(foodOrderBookingListOfRes, HttpStatus.OK);
    }
}
