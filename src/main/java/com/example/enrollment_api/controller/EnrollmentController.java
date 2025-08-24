package com.example.enrollment_api.controller;

import com.example.enrollment_api.entity.Enrollment;
import com.example.enrollment_api.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // This endpoint is restricted to STUDENT and COACH in SecurityConfig.java
    @PostMapping("/enroll")
    public ResponseEntity<?> enrollUser(@RequestParam Long userId, @RequestParam Long programId) {
        try {
            Enrollment newEnrollment = enrollmentService.enrollUser(userId, programId);
            return ResponseEntity.ok(newEnrollment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // This endpoint is restricted to STUDENT and COACH in SecurityConfig.java
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Enrollment>> getEnrolledPrograms(@PathVariable Long userId) {
        return ResponseEntity.ok(enrollmentService.getEnrolledProgramsForUser(userId));
    }
}
