package com.example.enrollment_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProgramDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long organizationId;
}