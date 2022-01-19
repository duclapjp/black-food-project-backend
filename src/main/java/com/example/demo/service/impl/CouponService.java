package com.example.demo.service.impl;

import com.example.demo.model.Coupon;
import com.example.demo.repository.ICouponRepository;
import com.example.demo.service.extend.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponService implements ICouponService {


    @Autowired
    private ICouponRepository couponRepository;

    @Override
    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }

    @Override
    public Optional<Coupon> findById(Long id) {
        return couponRepository.findById(id);
    }

    @Override
    public Coupon save(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public void remove(Long id) {
        couponRepository.deleteById(id);
    }

    @Override
    public List<Coupon> findAllByRestaurantId(Long restaurantId) {
        return couponRepository.findAllByRestaurantId(restaurantId);
    }
}
