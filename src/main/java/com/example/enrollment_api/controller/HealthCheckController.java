package com.example.enrollment_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/college")
    public String checkHealth() {
        return "✅ The enrollment-api is running successfully!";
    }
}