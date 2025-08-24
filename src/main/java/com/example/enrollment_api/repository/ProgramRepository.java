package com.example.enrollment_api.repository;

import com.example.enrollment_api.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}
