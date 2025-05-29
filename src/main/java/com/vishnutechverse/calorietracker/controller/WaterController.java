package com.vishnutechverse.calorietracker.controller;

import com.vishnutechverse.calorietracker.dto.WaterRequest;
import com.vishnutechverse.calorietracker.dto.WaterResponse;
import com.vishnutechverse.calorietracker.service.WaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/water")
public class WaterController {

    private final WaterService waterService;

    public WaterController(WaterService waterService) {
        this.waterService = waterService;
    }

    @PostMapping
    public ResponseEntity<WaterResponse> addWater(@RequestBody WaterRequest request) {
        return ResponseEntity.ok(waterService.addWater( request));
    }

    @GetMapping
    public ResponseEntity<List<WaterResponse>> getWaterLogs(Authentication authentication) {
        String username = authentication.getName();
        List<WaterResponse> waterLogs = waterService.getWaterLogs(username);
        return ResponseEntity.ok(waterLogs);
    }
}
