package com.vishnutechverse.calorietracker.service;

import com.vishnutechverse.calorietracker.dto.WaterRequest;
import com.vishnutechverse.calorietracker.dto.WaterResponse;
import com.vishnutechverse.calorietracker.entity.User;
import com.vishnutechverse.calorietracker.entity.WaterIntake;
import com.vishnutechverse.calorietracker.repository.UserRepository;
import com.vishnutechverse.calorietracker.repository.WaterIntakeRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WaterService {
    private final WaterIntakeRepository waterRepo;
    private final UserRepository userRepo;

    public WaterService(WaterIntakeRepository waterRepo, UserRepository userRepo) {
        this.waterRepo = waterRepo;
        this.userRepo = userRepo;
    }

    public WaterResponse addWater( WaterRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        WaterIntake water = new WaterIntake();
        water.setAmountInMl(request.getAmountInMl());
        water.setDate(LocalDate.parse(request.getDate()));
        water.setUser(user);

        WaterIntake saved = waterRepo.save(water);

        WaterResponse response = new WaterResponse();
        response.setId(saved.getId());
        response.setAmountInMl(saved.getAmountInMl());
        response.setDate(saved.getDate().toString());

        return response;
    }

    public List<WaterResponse> getWaterLogs(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<WaterIntake> waterList = waterRepo.findByUser(user);
        return waterList.stream().map(w -> {
            WaterResponse res = new WaterResponse();
            res.setId(w.getId());
            res.setAmountInMl(w.getAmountInMl());
            res.setDate(w.getDate().toString());
            return res;
        }).toList();
    }

}
