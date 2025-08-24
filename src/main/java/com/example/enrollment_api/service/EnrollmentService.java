package com.example.enrollment_api.service;

import com.example.enrollment_api.entity.Enrollment;
import com.example.enrollment_api.entity.Program;
import com.example.enrollment_api.entity.User;
import com.example.enrollment_api.repository.EnrollmentRepository;
import com.example.enrollment_api.repository.ProgramRepository;
import com.example.enrollment_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProgramRepository programRepository;

    public Enrollment enrollUser(Long userId, Long programId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Program program = programRepository.findById(programId).orElseThrow(() -> new RuntimeException("Program not found"));

        if (program.isExpired()) {
            throw new RuntimeException("Cannot enroll in an expired program.");
        }
        if (!user.getOrganization().getId().equals(program.getOrganization().getId())) {
            throw new RuntimeException("User can only enroll in programs of their own organization.");
        }
        if (enrollmentRepository.findByUserAndProgram(user, program).isPresent()) {
            throw new RuntimeException("User is already enrolled in this program.");
        }

        Enrollment enrollment = new Enrollment(user, program);
        return enrollmentRepository.save(enrollment);

    }

        public List<Enrollment> getEnrolledProgramsForUser(Long userId) {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            return enrollmentRepository.findByUser(user);
        }

}
