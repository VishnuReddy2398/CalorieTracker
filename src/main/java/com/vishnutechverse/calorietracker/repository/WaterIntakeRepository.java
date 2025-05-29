package com.vishnutechverse.calorietracker.repository;

import com.vishnutechverse.calorietracker.entity.User;
import com.vishnutechverse.calorietracker.entity.WaterIntake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaterIntakeRepository extends JpaRepository<WaterIntake, Long> {
    List<WaterIntake> findByUser(User user);
}
