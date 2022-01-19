package com.example.demo.controller;

import com.example.demo.model.Coupon;
import com.example.demo.service.extend.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private ICouponService couponService;

    @GetMapping
    public ResponseEntity<List<Coupon>> findAll() {
        return new ResponseEntity<>(couponService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coupon> findById(@PathVariable Long id) {
        return new ResponseEntity<>(couponService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Coupon> save(@RequestBody Coupon coupon) {
        return new ResponseEntity<>(couponService.save(coupon), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Coupon> update(@RequestBody Coupon coupon) {
        return new ResponseEntity<>(couponService.save(coupon), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Coupon> deleteById(@PathVariable Long id) {
        couponService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Coupon>> findAllByRestaurantId(@PathVariable Long restaurantId) {
        return new ResponseEntity<>(couponService.findAllByRestaurantId(restaurantId), HttpStatus.OK);
    }
}
