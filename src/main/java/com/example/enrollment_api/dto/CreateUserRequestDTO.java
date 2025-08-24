package com.example.enrollment_api.dto;

import lombok.Data;

@Data
public class CreateUserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long organizationId;
    private String role;
}
