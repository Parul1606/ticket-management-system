package com.tmdb.central_api.service;

import com.tmdb.central_api.dto.OrgDetailDto;
import com.tmdb.central_api.middleware.DbApiIntegration;
import com.tmdb.central_api.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrgService {

    @Autowired
    DbApiIntegration dbApiIntegration;


    public Object createOrganization(OrgDetailDto orgDetailDto){
        //OrgDetailDto
        //we need to map these details to actual organization model object.

        Organization organization = new Organization();
        organization.setName(orgDetailDto.getName());
        organization.setRegisteredName(orgDetailDto.getRegisteredName());
        organization.setAdminEmail(orgDetailDto.getAdminEmail());
        organization.setAdminName(orgDetailDto.getAdminName());
        organization.setPassword(orgDetailDto.getPassword());
        organization.setWebsiteUrl(orgDetailDto.getWebsiteUrl());
        organization.setAddress(orgDetailDto.getAddress());
        organization.setCompanySize(orgDetailDto.getCompanySize());
        organization.setCreatedAt(LocalDateTime.now());
        organization.setUpdatedAt(LocalDateTime.now());

        // we need to call database-api create organization endpoint
        // that endpoint will save organization details in database.

        return dbApiIntegration.callCreateOrganizationEndpoint(organization);
    }
}
