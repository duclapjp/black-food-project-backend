package com.example.demo.service.extend;

import com.example.demo.model.Coupon;
import com.example.demo.service.IGeneralService;

import java.util.List;

public interface ICouponService extends IGeneralService<Coupon> {
    List<Coupon> findAllByRestaurantId(Long restaurantId);
}
