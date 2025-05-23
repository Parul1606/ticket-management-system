package com.ticket_management_system.database_api.controller;

import com.ticket_management_system.database_api.models.Role;
import com.ticket_management_system.database_api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/save")
    public ResponseEntity saveRole(@RequestBody Role role){
        roleRepository.save(role);
        return new ResponseEntity(role, HttpStatus.OK);
    }

    @GetMapping("/{orgId}/{roleName}")
    public ResponseEntity getRoleByOrgId(@PathVariable UUID orgId,
                                         @PathVariable String roleName){
        Role role = roleRepository.getRoleByOrgIdAndRoleName(orgId, roleName);
        return new ResponseEntity(role, HttpStatus.OK);
    }
}
