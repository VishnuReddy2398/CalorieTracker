package com.vishnutechverse.calorietracker.repository;

import com.vishnutechverse.calorietracker.entity.MealItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealItemRepository extends JpaRepository<MealItem, Long> {
}
