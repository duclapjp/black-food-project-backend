package com.example.demo.controller;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.*;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.extend.IRestaurantService;
import com.example.demo.service.extend.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id) {
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/change-avatar")
    public ResponseEntity<?> updateAvatar(@RequestBody ChangeAvatar changeAvatar){
        User user = userDetailService.getCurrentUser();
        if (user.getUsername().equals("Anonymous")){
            return new ResponseEntity<>(new ResponMessage("Please Login!"),HttpStatus.OK);
        }
        else {
            user.setAvatar(changeAvatar.getAvatar());
            userService.save(user);
            return new ResponseEntity<>(new ResponMessage("Success!"), HttpStatus.OK);
        }

    }

    @PutMapping("/purchase")
    public ResponseEntity<?>purchase(@RequestBody Purchase purchase){
        User currentUser = userDetailService.getCurrentUser();
        currentUser.setAmount(currentUser.getAmount()+ purchase.getAmount());
        userService.save(currentUser);
        return new ResponseEntity<>(currentUser,HttpStatus.OK);
    }
    @PutMapping("/addOrder")
    public ResponseEntity<?> addOrder(@RequestBody FoodOrder foodOrder){
        foodOrder.setTime(LocalDateTime.now());
        List<FoodOrder> foodOrderList = new ArrayList<>();
        foodOrderList.add(foodOrder);
        User currentUser = userDetailService.getCurrentUser();
        currentUser.setFoodOrderList(foodOrderList);
       User user = userService.save(currentUser);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PutMapping("/addFood")
    public ResponseEntity<?> addFood(@RequestBody Food food){
        food.setCode(Math.round(Math.random()*10000L));
        User currentUser = userDetailService.getCurrentUser();
        List<FoodOrder> foodOrderList = currentUser.getFoodOrderList();
        if (foodOrderList.isEmpty()){
            List<Food> foodList = new ArrayList<>();
            foodList.add(food);
            FoodOrder foodOrder = new FoodOrder(LocalDateTime.now(),0.0,null,new GeneralStatus(4L),foodList,currentUser);
           foodOrderList.add(foodOrder);
           currentUser.setFoodOrderList(foodOrderList);
            User user = userService.save(currentUser);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        else {
//            th neu co 1 order status 4
            for (FoodOrder foodOrder: foodOrderList) {
                if (foodOrder.getGeneralStatus().getId() == 4){
                    List<Food> foodList =  foodOrder.getFood();
                   foodList.add(food);
                   foodOrder.setFood(foodList);
                    currentUser.setFoodOrderList(foodOrderList);
                    User user = userService.save(currentUser);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
            }
//            th list order khong rong nhung kco order nao status 4
            List<Food> foodList = new ArrayList<>();
            foodList.add(food);
            FoodOrder foodOrder = new FoodOrder(LocalDateTime.now(),0.0,null,new GeneralStatus(4L),foodList,currentUser);
            foodOrderList.add(foodOrder);
            currentUser.setFoodOrderList(foodOrderList);
            User user = userService.save(currentUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
    @GetMapping("/currentFO")
    public ResponseEntity<FoodOrder> showCurrentFO() {
        User user = userDetailService.getCurrentUser();
        List<FoodOrder> foodOrderList = new ArrayList<>();
        foodOrderList = user.getFoodOrderList();
        FoodOrder foodOrder = new FoodOrder();
        for (FoodOrder fo : foodOrderList
        ) {
            if (fo.getGeneralStatus().getId() == 4) {
                foodOrder = fo;
                return new ResponseEntity<>(foodOrder, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(foodOrder, HttpStatus.OK);
    }
    @PutMapping("/updateFoodList")
    public ResponseEntity<User> updateFoodList(@RequestBody List<Food> foodList){
        User user = userDetailService.getCurrentUser();
        List<FoodOrder> foodOrderList = new ArrayList<>();
        foodOrderList = user.getFoodOrderList();
        for (FoodOrder fo : foodOrderList
        ) {
            if (fo.getGeneralStatus().getId() == 4) {
              fo.setFood(foodList);
            }
        }
        user.setFoodOrderList(foodOrderList);
        userService.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PutMapping("/payment")
    public ResponseEntity<?>payment(@RequestBody Payment payment){
        User user = userDetailService.getCurrentUser();
        Double amount = user.getAmount();
        Double totalPrice = payment.getTotalPrice();
        if (amount>= totalPrice){
            amount -= totalPrice;
            //        tru tien trong tai khoan user
            user.setAmount(amount);
//            chuyen status cua order thanh done
            List<FoodOrder> foodOrderList = new ArrayList<>();
            foodOrderList = user.getFoodOrderList();
            for (FoodOrder fo : foodOrderList
            ) {
                if (fo.getGeneralStatus().getId() == 4) {
                    fo.setGeneralStatus(new GeneralStatus(5L));
                    // cong tien cua food tuong ung vao cua hang
                    for (Food f: fo.getFood()) {
                        Restaurant restaurant = restaurantService.findById(f.getRestaurant().getId()).get();
                        Double interest = f.getPrice();
                        restaurant.setRevenue(restaurant.getRevenue()+interest);
                        restaurantService.save(restaurant);
                    }
                }

            }
            user.setFoodOrderList(foodOrderList);
            User user1 =userService.save(user);

            return new ResponseEntity<>(user1,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ResponMessage("No"),HttpStatus.OK);
        }
    }
}
