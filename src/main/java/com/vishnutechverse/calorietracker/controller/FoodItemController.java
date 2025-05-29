package com.vishnutechverse.calorietracker.controller;

import com.vishnutechverse.calorietracker.entity.FoodItem;
import com.vishnutechverse.calorietracker.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-items")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public List<FoodItem> getAllItems() {
        return foodItemService.getAllItems();
    }

    @GetMapping("/{name}")
    public FoodItem getByName(@PathVariable String name) {
        return foodItemService.getByName(name)
                .orElseThrow(() -> new RuntimeException("Food item not found"));
    }
}
