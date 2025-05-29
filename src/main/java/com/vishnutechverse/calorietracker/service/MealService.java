package com.vishnutechverse.calorietracker.service;

import com.vishnutechverse.calorietracker.dto.AddMealRequest;
import com.vishnutechverse.calorietracker.entity.*;
import com.vishnutechverse.calorietracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private MealRepository mealRepository;

    public void addMeal(AddMealRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        Meal meal = new Meal();
        meal.setMealTime(LocalDateTime.now());
        meal.setUser(user);

        double totalCalories = 0;

        for (AddMealRequest.MealFoodItem mealFoodItem : request.getItems()) {
            String itemName = mealFoodItem.getItemName();
            int quantity = mealFoodItem.getQuantity();

            FoodItem foodItem = foodItemRepository.findByItemNameIgnoreCase(itemName.trim())
                    .orElseThrow(() -> new RuntimeException("Food item not found: " + itemName));

            MealItem mealItem = new MealItem();
            mealItem.setMeal(meal);
            mealItem.setFoodItem(foodItem);
            mealItem.setQuantity(quantity);

            double calories = quantity * foodItem.getCalories();
            mealItem.setCalories(calories);

            meal.getMealItems().add(mealItem);

            totalCalories += calories;
        }

        meal.setTotalCalories(totalCalories);

        mealRepository.save(meal);
    }
    public List<Meal> getMealsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return mealRepository.findByUser(user);
    }
    public List<Meal> getMealsByUsernameAndDateRange(String username, LocalDate startDate, LocalDate endDate) {
        // Convert LocalDate to LocalDateTime for start and end of days
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        return mealRepository.findByUserUsernameAndMealTimeBetween(username, startDateTime, endDateTime);
    }

}
