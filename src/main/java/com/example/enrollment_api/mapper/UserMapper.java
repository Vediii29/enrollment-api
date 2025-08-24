package com.example.enrollment_api.mapper;

import com.example.enrollment_api.dto.UserDTO;
import com.example.enrollment_api.entity.User;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        if (user.getOrganization() != null) {
            userDTO.setOrganizationId(user.getOrganization().getId());
        }

        return userDTO;
    }
}