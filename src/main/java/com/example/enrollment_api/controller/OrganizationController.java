package com.example.enrollment_api.controller;


import com.example.enrollment_api.entity.Organization;
import com.example.enrollment_api.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public Organization createOrganization(@RequestBody Organization organization){
        return organizationService.createOrganization(organization);
    }

    @GetMapping
    public List<Organization> getAllOrganizations(){
        return organizationService.getAllOrganizations();
    }

    @GetMapping("{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable Long id){
        return organizationService.getOrganizationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Organization> updateOrganization(@PathVariable Long id, @RequestBody Organization organizationDetails){
        try{
            return ResponseEntity.ok(organizationService.updateOrganization(id, organizationDetails));
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        try {
            organizationService.deleteOrganization(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
