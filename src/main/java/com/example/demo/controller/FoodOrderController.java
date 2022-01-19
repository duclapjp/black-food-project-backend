package com.example.demo.controller;
import com.example.demo.model.Food;
import com.example.demo.model.FoodOrder;
import com.example.demo.service.extend.IFoodOrderService;
import com.example.demo.service.extend.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/foodOrders")
public class FoodOrderController {

    @Autowired
    private IFoodOrderService foodOrderService;

    @Autowired
    private IFoodService foodService;

    @GetMapping
    public ResponseEntity<List<FoodOrder>> findAll(){
        return new ResponseEntity<>(foodOrderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> findById(@PathVariable Long id){
        Optional<FoodOrder>foodOrderOptional = foodOrderService.findById(id);
        return new ResponseEntity<>(foodOrderService.findById(id).get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FoodOrder> save(@RequestBody FoodOrder foodOrder){
        Optional<Food> foodOptional = foodService.findById(11L);
        Optional<Food> foodOptional1 = foodService.findById(12L);
        Optional<Food> foodOptional2 = foodService.findById(13L);
        List<Food> foodList = new ArrayList<>();
        foodList.add(foodOptional1.get());
        foodList.add(foodOptional2.get());
        foodList.add(foodOptional.get());
        foodOrder.setFoodList(foodList);
        return new ResponseEntity<>(foodOrderService.save(foodOrder),HttpStatus.CREATED);
    }
    @PutMapping("/{foodOrderId}/{foodId}")
    public ResponseEntity<?> addFoodIntoFoodOrder(@PathVariable Long foodOrderId, @PathVariable Long foodId){
        FoodOrder foodOrder = foodOrderService.findById(foodOrderId).get();
        Optional<Food> foodOptional = foodService.findById(foodId);
        foodOrder.getFoodList().add(foodOptional.get());
        return  new ResponseEntity<>(foodOrderService.save(foodOrder),HttpStatus.OK);
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
}
