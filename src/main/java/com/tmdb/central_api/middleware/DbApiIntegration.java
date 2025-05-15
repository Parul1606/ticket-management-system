package com.tmdb.central_api.middleware;


import com.tmdb.central_api.models.Organization;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class DbApiIntegration {

    public Object callCreateOrganizationEndpoint(Organization organization){
        //this function will call/organization/create endpoint of the db api

        //step1: create endpoint
        String endpoint = "http://localhost:8080/api/v1/db/organization/create";

        //step2: create a request
        RequestEntity request = RequestEntity.post(endpoint).body(organization);

        //step3: hit the request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(endpoint, HttpMethod.POST, request, Object.class);  //when this exchange method will get called at that time we will be hitting our request to the db api
        return response.getBody();

    }
}
