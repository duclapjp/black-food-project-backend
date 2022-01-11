package com.example.demo.controller;

import com.example.demo.model.FoodOrder;
import com.example.demo.service.extend.IFoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/foodOrders")
public class FoodOrderController {

    @Autowired
    private IFoodOrderService foodOrderService;

    @GetMapping
    public ResponseEntity<List<FoodOrder>> findAll(){
        return new ResponseEntity<>(foodOrderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> findById(@PathVariable Long id){
        return new ResponseEntity<>(foodOrderService.findById(id).get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FoodOrder> save(@RequestBody FoodOrder foodOrder){
        return new ResponseEntity<>(foodOrderService.save(foodOrder),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<FoodOrder> update( @RequestBody FoodOrder foodOrder){
        return new ResponseEntity<>(foodOrderService.save(foodOrder),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        foodOrderService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/orderList/{id}")
    public ResponseEntity<List<FoodOrder>> findAllFoodOrderByUser_Id(@PathVariable Long id){
        List<FoodOrder> foodOrderList  = foodOrderService.findAllByUser_Id(id);
        return new ResponseEntity<>(foodOrderList,HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<FoodOrder>> findAllByRestaurantId(@PathVariable Long id){
        List<FoodOrder> foodOrderList = foodOrderService.findFoodOrderByRestaurant_Id(id);
        return new ResponseEntity<>(foodOrderList,HttpStatus.OK);
    }
}
