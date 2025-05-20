package com.tmdb.central_api.middleware;

import com.tmdb.central_api.models.Organization;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationAPIConnector {

    String baseUrl = "http://localhost:8082/api/v1/notify";

    public void callOrgCreateNotificationEndpoint(Organization organization){
        //this function is going to make request to notification api
        // org/create component
        String url = baseUrl + "/org/create";
        RequestEntity request = RequestEntity
                .put(url)
                .body(organization);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> resp = restTemplate.exchange(url, HttpMethod.PUT, request, Object.class);
    }
}
