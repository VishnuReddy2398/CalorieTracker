package com.vishnutechverse.calorietracker.repository;

import com.vishnutechverse.calorietracker.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {
    Optional<FoodItem> findByItemNameIgnoreCase(String itemName);
}
