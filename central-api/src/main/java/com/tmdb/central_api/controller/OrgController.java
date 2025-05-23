package com.tmdb.central_api.controller;

import com.tmdb.central_api.dto.OrgDetailDto;
import com.tmdb.central_api.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/central/org")
public class OrgController {

    @Autowired
    OrgService orgService;


    @PostMapping("/registration")
    public ResponseEntity createOrganization(@RequestBody OrgDetailDto orgDetailDto){
        // we will get organization details as requestbody
        // but question is that organization details should
        // be same as organization model designed in db api.
        Object response = orgService.createOrganization(orgDetailDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
