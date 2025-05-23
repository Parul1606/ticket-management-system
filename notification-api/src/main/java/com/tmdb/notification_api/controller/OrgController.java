package com.tmdb.notification_api.controller;

import com.tmdb.notification_api.models.Organization;
import com.tmdb.notification_api.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notify/org")
public class OrgController {

    @Autowired
    OrgService orgService;

    @PutMapping("/create")
    public void notifyAdminForOrgCreation(@RequestBody Organization organization) throws Exception{
        //now we need to call org service -> this org service is going to send mail for us
        orgService.notifyAdminForOrgCreation(organization);

    }
}
