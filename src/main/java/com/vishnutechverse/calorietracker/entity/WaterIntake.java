package com.vishnutechverse.calorietracker.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class WaterIntake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amountInMl;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
