package com.vishnutechverse.calorietracker.controller;

import com.vishnutechverse.calorietracker.dto.AddMealRequest;
import com.vishnutechverse.calorietracker.entity.Meal;
import com.vishnutechverse.calorietracker.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @PostMapping
    public ResponseEntity<String> addMeal(@RequestBody AddMealRequest request) {
        mealService.addMeal(request);
        return ResponseEntity.ok("Meal added successfully!");
    }

    @GetMapping
    public ResponseEntity<List<Meal>> getMealsForUser(Authentication authentication) {
        String username = authentication.getName(); // Get logged-in user's username from JWT
        List<Meal> meals = mealService.getMealsByUsername(username);
        return ResponseEntity.ok(meals);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Meal>> getMealsForUserByDateRange(
            Authentication authentication,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        String username = authentication.getName();
        List<Meal> meals = mealService.getMealsByUsernameAndDateRange(username, startDate, endDate);
        return ResponseEntity.ok(meals);
    }


}
