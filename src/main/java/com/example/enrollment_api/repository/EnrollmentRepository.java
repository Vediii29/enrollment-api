package com.example.enrollment_api.repository;

import com.example.enrollment_api.entity.Enrollment;
import com.example.enrollment_api.entity.Program;
import com.example.enrollment_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByUserAndProgram(User user, Program program);
    List<Enrollment> findByUser(User user);
}
