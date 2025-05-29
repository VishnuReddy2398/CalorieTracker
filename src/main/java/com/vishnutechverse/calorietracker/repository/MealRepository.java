package com.vishnutechverse.calorietracker.repository;

import com.vishnutechverse.calorietracker.entity.Meal;
import com.vishnutechverse.calorietracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUser(User user);

    List<Meal> findByUserUsernameAndMealTimeBetween(String username, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
