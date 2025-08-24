package com.example.enrollment_api.controller;

import com.example.enrollment_api.entity.Program;
import com.example.enrollment_api.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
// These endpoints are restricted to ADMIN in SecurityConfig.java
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @PostMapping
    public Program createProgram(@RequestBody Program program, @RequestParam Long organizationId) {
        return programService.createProgram(program, organizationId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Program> updateProgram(@PathVariable Long id, @RequestBody Program programDetails) {
        try {
            return ResponseEntity.ok(programService.updateProgram(id, programDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        try {
            programService.deleteProgram(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Program> getAllPrograms() {
        return programService.getAllPrograms();
    }
}