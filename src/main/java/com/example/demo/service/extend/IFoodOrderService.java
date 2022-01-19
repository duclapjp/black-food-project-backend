package com.example.demo.service.extend;

import com.example.demo.model.FoodOrder;
import com.example.demo.service.IGeneralService;
import java.util.List;
import java.util.Map;

public interface IFoodOrderService extends IGeneralService<FoodOrder> {
        List<FoodOrder> findAllByUser_Id(Long id);
}
