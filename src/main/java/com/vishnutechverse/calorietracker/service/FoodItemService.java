package com.vishnutechverse.calorietracker.service;

import com.vishnutechverse.calorietracker.entity.FoodItem;
import com.vishnutechverse.calorietracker.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    public List<FoodItem> getAllItems() {
        return foodItemRepository.findAll();
    }

    public Optional<FoodItem> getByName(String name) {
        return foodItemRepository.findByItemNameIgnoreCase(name);
    }
}
