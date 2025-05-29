package com.vishnutechverse.calorietracker.dto;

public class WaterResponse {
    private Long id;
    private int amountInMl;
    private String date;

    public WaterResponse(){

    }
    public WaterResponse(Long id, int amountInMl, String date) {
        this.id = id;
        this.amountInMl = amountInMl;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
