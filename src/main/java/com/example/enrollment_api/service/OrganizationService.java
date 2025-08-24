package com.example.enrollment_api.service;

import com.example.enrollment_api.entity.Organization;
import com.example.enrollment_api.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization createOrganization(Organization organization){
        return organizationRepository.save(organization);
    }

    public Optional<Organization> getOrganizationById(Long id){
        return organizationRepository.findById(id);
    }

    public List<Organization> getAllOrganizations(){
        return organizationRepository.findAll();
    }

    public Organization updateOrganization(Long id, Organization organizationDetails) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found with id: " + id));
        organization.setName(organizationDetails.getName());
        return organizationRepository.save(organization);
    }

    public void deleteOrganization(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found with id: " + id));
        organizationRepository.delete(organization);
    }
}
