package com.example.enrollment_api.service;

import com.example.enrollment_api.dto.CreateUserRequestDTO;
import com.example.enrollment_api.entity.*;
import com.example.enrollment_api.repository.OrganizationRepository;
import com.example.enrollment_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateUserRequestDTO userDTO){
        Organization org = organizationRepository.findById(userDTO.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        User user;
        String role = userDTO.getRole().toUpperCase();

        switch(role){
            case "STUDENT":
                user = new Student();
                break;
            case "COACH":
                user = new Coach();
                break;
            case "ADMIN":
                user = new Admin();
                break;
            case "SUPER_ADMIN":
                user = new SuperAdmin();
                break;
            default:
                throw new RuntimeException("Invalid user role specified");
        }
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setOrganization(org);

        return userRepository.save(user);
    }
}
