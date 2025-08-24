package com.example.enrollment_api;

import com.example.enrollment_api.entity.SuperAdmin;
import com.example.enrollment_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EnrollmentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrollmentApiApplication.class, args);
	}


	@Bean
	CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Create a Super Admin user only if one doesn't already exist
			if (userRepository.findByEmail("superadmin@test.com").isEmpty()) {
				SuperAdmin superAdmin = new SuperAdmin();
				superAdmin.setFirstName("Super");
				superAdmin.setLastName("Admin");
				superAdmin.setEmail("superadmin@test.com");
				superAdmin.setPassword(passwordEncoder.encode("password"));
				userRepository.save(superAdmin);
				System.out.println(">>>> Super Admin user created!");
			}
		};
	}
}