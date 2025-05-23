package com.tmdb.auth_api.connector;

import com.tmdb.auth_api.model.Employee;
import com.tmdb.auth_api.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * ✅ DBAPI - External API Connector to DB Microservice
 *
 * 📌 Purpose:
 * - Fetch Employee details using email
 * - Fetch Role details using organization ID and role name
 *
 * 🧠 Works like a mini client using RestTemplate
 */
public class DBAPI {

    // Inject base URL for the DB microservice from application.properties
    @Value("${db.api.base.url}")
    String baseUrl;

    /**
     * 🔍 Fetch Employee by Email ID
     * 📤 Endpoint: GET {baseUrl}/employee/email/{emailId}
     * 📥 Input: emailId (String)
     * 📤 Output: Employee object
     */
    public Employee callGetEmployeeByEmailEndpoint(String emailId){

        // Build full endpoint URL
        String url = baseUrl + "/employee/email/" + emailId;

        // Create HTTP GET request
        RequestEntity request = RequestEntity.get(url).build();

        // Create a RestTemplate instance to send the request
        RestTemplate restTemplate = new RestTemplate();

        // Exchange request and get response
        ResponseEntity<Employee> resp =  restTemplate.exchange(url, HttpMethod.GET, request, Employee.class);

        // Return response body (Employee)
        return resp.getBody();
    }

    /**
     * 🔍 Fetch Role by Org ID and Role Name
     * 📤 Endpoint: GET {baseUrl}/role/{orgId}/{roleName}
     * 📥 Input: orgId (UUID), roleName (String)
     * 📤 Output: Role object
     */
    public Role callGetRoleByOrgIdEndpoint(UUID orgId, String roleName){

        // Build full endpoint URL
        String url = baseUrl + "/role/" + orgId.toString() + "/" + roleName;

        // Create HTTP GET request
        RequestEntity request = RequestEntity.get(url).build();

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Exchange request and get response
        ResponseEntity<Role> resp = restTemplate.exchange(url, HttpMethod.GET, request, Role.class);

        // Return response body (Role)
        return resp.getBody();
    }
}

/*
 --------------------------------------------
 🧠 Keyword Reference - DBAPI
 --------------------------------------------
 @Value("${key}")            => Injects property value from application.properties
 RestTemplate                => Spring utility to make REST calls (synchronous)
 RequestEntity               => Represents the full HTTP request (method, headers, URI)
 ResponseEntity<T>           => Represents the full HTTP response including status and body
 .exchange()                 => Generic method to send any HTTP method (GET, POST, etc.)
 .getBody()                  => Extracts just the response body (here: Employee or Role)

 🌐 Example application.properties entry:
 db.api.base.url = http://localhost:8081/api/v1
*/