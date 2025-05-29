package com.vishnutechverse.calorietracker.dto;

import java.util.List;

public class AddMealRequest {

    private List<MealFoodItem> items;

    public List<MealFoodItem> getItems() {
        return items;
    }

    public void setItems(List<MealFoodItem> items) {
        this.items = items;
    }

    public static class MealFoodItem {
        private String itemName;
        private int quantity;

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
