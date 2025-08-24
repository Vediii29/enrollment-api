package com.example.enrollment_api.service;

import com.example.enrollment_api.entity.Organization;
import com.example.enrollment_api.entity.Program;
import com.example.enrollment_api.repository.OrganizationRepository;
import com.example.enrollment_api.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    public Program createProgram(Program program, Long organizationId){
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() ->new RuntimeException("Organization not found with this id: "+organizationId));
        program.setOrganization(organization);
        return programRepository.save(program);
    }
    public Program updateProgram(Long programId, Program programDetails) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found with id: " + programId));

        program.setName(programDetails.getName());
        program.setStartDate(programDetails.getStartDate());
        program.setEndDate(programDetails.getEndDate());

        return programRepository.save(program);
    }

    public void deleteProgram(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found with id: " + programId));
        programRepository.delete(program);
    }

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }
}
