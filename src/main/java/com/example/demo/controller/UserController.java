package com.example.demo.controller;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.*;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.extend.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin( "*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private IFoodService foodService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IGeneralStatusService generalStatusService;

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
//        Food food1 = foodService.findById(food.getId()).get();
//        Food food2 = new Food(food1.getId(),food1.getName(),food1.getPrice(),food1.getQuantity(),food1.getDescription(),food1.getImage(),food1.getRestaurantId());
        User currentUser = userDetailService.getCurrentUser();
        Long userId = currentUser.getId();
        List<FoodOrder> foodOrderList = currentUser.getFoodOrderList();
//        if (foodOrderList.isEmpty()){
//            List<Food> foodList = new ArrayList<>();
//            foodList.add(food);
//            FoodOrder foodOrder = new FoodOrder(LocalDateTime.now(),0.0,null,new GeneralStatus(4L),foodList,userId);
//           foodOrderList.add(foodOrder);
//           currentUser.setFoodOrderList(foodOrderList);
//            User user = userService.save(currentUser);
//            return new ResponseEntity<>(user,HttpStatus.OK);
//        }
//        else {
//            th neu co 1 order status 4
            for (FoodOrder foodOrder: foodOrderList) {
                if (foodOrder.getGeneralStatus().getId() == 4){
                    if (foodOrder.getFoodList().isEmpty()){
                        foodOrder.setTime(LocalDateTime.now());
                    }
                    List<Food> foodList =  foodOrder.getFoodList();
                    foodList.add(food);
//                    Xu ly cho quantity
//                    if (foodList.isEmpty()){
//                        //                        khong ton tai thi add moi food voi quantity bang 1
//                        food2.setQuantity(1L);
//                        foodList.add(food2);
//                    }
//                    else {
//                        //                    Xet xem food da ton tai trong list hay chua,
//                        for (Food f: foodList) {
////                        ton tai thi cong quantity len 1
//                            if (f.getId() == food.getId()){
//                                f.setQuantity(f.getQuantity()+1L);
//                                break;
//                            }
//                            else {
//                                food2.setQuantity(1L);
//                                foodList.add(food2);
//                            }
//
//                        }
//                    }

                   foodOrder.setFoodList(foodList);
                    currentUser.setFoodOrderList(foodOrderList);
                    User user = userService.save(currentUser);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
            }
//            th list order khong rong nhung kco order nao status 4
//            List<Food> foodList = new ArrayList<>();
//            foodList.add(food);
//            FoodOrder foodOrder = new FoodOrder(LocalDateTime.now(),0.0,null,new GeneralStatus(4L),foodList,userId);
//            foodOrderList.add(foodOrder);
//            currentUser.setFoodOrderList(foodOrderList);
//            User user = userService.save(currentUser);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
        return new ResponseEntity<>(new ResponMessage("error"),HttpStatus.OK);
    }
    @GetMapping("/currentFO")
    public ResponseEntity<?> showCurrentFO() {
        User user = userDetailService.getCurrentUser();
        List<FoodOrder> foodOrderList = new ArrayList<>();
        foodOrderList = user.getFoodOrderList();
        List<Food> foodList = new ArrayList<>();
        FoodOrder foodOrder = new FoodOrder (0.0,"",new GeneralStatus(4L),foodList,user.getId());
        for (FoodOrder fo : foodOrderList
        ) {
            if (fo.getGeneralStatus().getId() == 4) {
                foodOrder = fo;
                return new ResponseEntity<>(foodOrder, HttpStatus.OK);
            }
            else if (fo.getGeneralStatus().getId() == 5){
                foodOrder = fo;
                return new ResponseEntity<>(foodOrder, HttpStatus.OK);
            }
        }
//        foodOrderList.add(foodOrder);
        user.getFoodOrderList().add(foodOrder);
//        user.setFoodOrderList(foodOrderList);
        User user1 = userService.save(user);
        for (FoodOrder fo : user1.getFoodOrderList()
        ) {
            if (fo.getGeneralStatus().getId() == 4) {
                foodOrder = fo;
                return new ResponseEntity<>(foodOrder, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ResponMessage("error"), HttpStatus.OK);
    }
    @PutMapping("/updateFoodList")
    public ResponseEntity<User> updateFoodList(@RequestBody List<Food> foodList){
        User user = userDetailService.getCurrentUser();
        List<FoodOrder> foodOrderList = new ArrayList<>();
        foodOrderList = user.getFoodOrderList();
        for (FoodOrder fo : foodOrderList
        ) {
            if (fo.getGeneralStatus().getId() == 4) {
              fo.setFoodList(foodList);
            }
        }
        user.setFoodOrderList(foodOrderList);
        userService.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/bookingOrder")
    public ResponseEntity<?> bookingOrder(){
        Long resId = 0L;
        Double totalPrice = 0.0;
        User user = userDetailService.getCurrentUser();
        List<FoodOrder> foodOrderList = new ArrayList<>();
        foodOrderList = user.getFoodOrderList();
        for (FoodOrder fo: foodOrderList) {
//            Chuyen trang thai inprocess thanh booking
            if (fo.getGeneralStatus().getId() == 4) {
                for (Food f: fo.getFoodList()) {
                    totalPrice += f.getPrice();
                }
                fo.setTotalPrice(totalPrice);
                fo.setGeneralStatus(new GeneralStatus(5L));
                for (Food f: fo.getFoodList()) {
//                    Lay ra res tuong ung
                    resId = f.getRestaurantId();
                    break;
                }
                Restaurant restaurant = restaurantService.findById(resId).get();
//                    Add fo vao res
                List<FoodOrder> foListOfRes = restaurant.getFoodOrderList();
                foListOfRes.add(fo);
                restaurant.setFoodOrderList(foListOfRes);
                restaurantService.save(restaurant);
                break;
            }
        }
        user.setFoodOrderList(foodOrderList);
        User user1 =  userService.save(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
    @GetMapping("/cancelFO")
    public ResponseEntity<?>cancelFO(){
        Long resId = 0L;
        User user = userDetailService.getCurrentUser();
        List<FoodOrder> foodOrderListOfUser = new ArrayList<>();
        foodOrderListOfUser = user.getFoodOrderList();
        for (FoodOrder fo: foodOrderListOfUser) {
            if (fo.getGeneralStatus().getId() == 5) {
//                Thuc hien chuyen doi status fo cua user thanh cancel
                fo.setGeneralStatus(new GeneralStatus(7L));
                for (Food f: fo.getFoodList()) {
                     resId = f.getRestaurantId();
                     break;

                        }
                //                    Thuc hien tra ve nha hang tuong ung
                Restaurant restaurant = restaurantService.findById(resId).get();
                List<FoodOrder> foOfRes = restaurant.getFoodOrderList();
                for (FoodOrder resFo: foOfRes) {
                    if (fo.getId() == resFo.getId()){
                        resFo.setGeneralStatus(new GeneralStatus(7L));
                        break;
                    }
                }
                //                            Set lai fo list cua res
                restaurant.setFoodOrderList(foOfRes);
                restaurantService.save(restaurant);
                break;
            }
        }
        //                            Set lai fo list cua user
        user.setFoodOrderList(foodOrderListOfUser);
        User user1 = userService.save(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
    @PutMapping("/{userId}/{foId}/{resId}/payment")
    public ResponseEntity<?>payment(@PathVariable Long userId,@PathVariable Long foId,@PathVariable Long resId, @RequestBody Payment payment){
        User user = userService.findById(userId).get();
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
                if (fo.getGeneralStatus().getId() == 5) {
                    fo.setGeneralStatus(new GeneralStatus(6L));
                   break;

                }
            }
            Restaurant restaurant = restaurantService.findById(resId).get();
            restaurant.setRevenue(restaurant.getRevenue() + payment.getTotalPrice());
            List<FoodOrder> foodOrderListOfRes = new ArrayList<>();
            foodOrderListOfRes = restaurant.getFoodOrderList();
            for (FoodOrder fo:foodOrderListOfRes ) {
                if (fo.getId() == foId) {
                    fo.setGeneralStatus(new GeneralStatus(6L));
                    break;
                }
            }
            restaurant.setFoodOrderList(foodOrderListOfRes);
            restaurantService.save(restaurant);
            user.setFoodOrderList(foodOrderList);
            User user1 =userService.save(user);

            return new ResponseEntity<>(user1,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ResponMessage("No"),HttpStatus.OK);
        }
    }
    @GetMapping("/merchantRegister")
    public ResponseEntity<?> merchantRegister(){
        User user = userDetailService.getCurrentUser();
        Set<Role> roleSet = user.getRoles();
        Role merchantRole = roleService.findByName(RoleName.MERCHANT).get();
        merchantRole.setStatus(generalStatusService.findById(1L).get());;
        roleSet.add(merchantRole);
        user.setRoles(roleSet);
        userService.save(user);
        return new ResponseEntity<>(new ResponMessage("Success!"),HttpStatus.OK);
    }
}
