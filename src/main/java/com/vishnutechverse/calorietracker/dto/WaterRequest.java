package com.vishnutechverse.calorietracker.dto;

public class WaterRequest {
    private int amountInMl;
    private String date; // or LocalDate

    public WaterRequest(){

    }

    public WaterRequest(int amountInMl, String date) {
        this.amountInMl = amountInMl;
        this.date = date;
    }

    public int getAmountInMl() {
        return amountInMl;
    }

    public void setAmountInMl(int amountInMl) {
        this.amountInMl = amountInMl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
