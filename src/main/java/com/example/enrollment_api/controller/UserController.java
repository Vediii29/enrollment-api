package com.example.enrollment_api.controller;

import com.example.enrollment_api.dto.CreateUserRequestDTO;
import com.example.enrollment_api.dto.UserDTO; // Import UserDTO
import com.example.enrollment_api.entity.User;
import com.example.enrollment_api.mapper.UserMapper; // Import the new mapper
import com.example.enrollment_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequestDTO userDTO) { // Return UserDTO
        User newUser = userService.createUser(userDTO);
        // Convert the User entity to a UserDTO before returning
        return ResponseEntity.ok(UserMapper.toUserDTO(newUser));
    }
}