package com.ticket_management_system.database_api.controller;

import com.ticket_management_system.database_api.models.Organization;
import com.ticket_management_system.database_api.repository.OrganizationRepository;
import jakarta.persistence.GeneratedValue;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/organization")
public class OrganizationController {

    @Autowired
    OrganizationRepository organizationRepository;

    @PostMapping("/create")
    public ResponseEntity createOrganization(@RequestBody Organization organization){
        organizationRepository.save(organization);
        return new ResponseEntity(organization, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrganizationById(@PathVariable UUID id){
        Organization organization = organizationRepository.findById(id).orElse(null);
        return new ResponseEntity(organization, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrganizationById(@PathVariable UUID id){
        organizationRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity updateOrganization(@RequestBody Organization organization){
        organizationRepository.save(organization);
        return new ResponseEntity(organization, HttpStatus.CREATED);
    }
}
