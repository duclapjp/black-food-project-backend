package com.example.demo.repository;
import com.example.demo.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface IFoodOrderRepository  extends JpaRepository<FoodOrder,Long> {

    @Query(value = "select fo.*,u.name as name from food_order fo join users u on fo.user_id = u.id where fo.user_id = :id ;",nativeQuery = true)
    List<FoodOrder> findAllByUser_Id(@Param("id") Long id);
}
