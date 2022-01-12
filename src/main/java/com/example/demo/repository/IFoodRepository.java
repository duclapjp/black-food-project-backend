package com.example.demo.repository;

import com.example.demo.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface IFoodRepository extends JpaRepository<Food,Long> {
@Query(value = "select * from food",nativeQuery = true)
    List<Food> findAll();
}
