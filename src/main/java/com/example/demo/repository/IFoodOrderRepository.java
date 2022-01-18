package com.example.demo.repository;
import com.example.demo.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface IFoodOrderRepository  extends JpaRepository<FoodOrder,Long> {

    List<FoodOrder> findAllByUserId(Long id);

//    @Query(value = "select fo.*,r.name as restaurantName, r.address as restaurantAddress from food_order fo join restaurant r on fo.restaurant_id = r.id where r.id = :id ;",nativeQuery = true)
//    List<FoodOrder> findFoodOrderByRestaurant_Id(@Param("id") Long id);
}
