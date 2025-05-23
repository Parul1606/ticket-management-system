package com.tmdb.auth_api.service;

import com.tmdb.auth_api.connector.DBAPI;
import com.tmdb.auth_api.model.Operation;
import com.tmdb.auth_api.model.Role;
import com.tmdb.auth_api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * ✅ AuthService - Handles Authorization Logic
 *
 * 📌 Main Purpose:
 * - Check if a user (based on their role from token) has access to perform a specific operation
 */
@Service
public class AuthService {

    // Inject DBAPI for external DB service calls
    @Autowired
    DBAPI dbapi;

    // Inject JwtUtil for token operations (decode/decrypt)
    @Autowired
    JwtUtil jwtUtil;

    /**
     * 🔐 Check if the user's role (from token) allows access to a given operation within an organization
     *
     * @param token    Encrypted JWT token containing user info
     * @param orgId    Organization ID to validate role in that context
     * @param oprName  Name of the operation (like "READ_REPORT", "DELETE_USER" etc.)
     * @return         true if allowed, false otherwise
     */
    public boolean isValidAccess(String token,
                                 UUID orgId,
                                 String oprName){

        // 🔓 Decrypt token to extract user info (email, password, role)
        String information = jwtUtil.decryptToken(token);

        // 🪓 Split by ": " to isolate role (assuming format is "email: password: role")
        String[] infoArr = information.split(": ");
        String role = infoArr[2];

        // 🏢 Fetch Role object for this user role under this organization
        Role roleDb = dbapi.callGetRoleByOrgIdEndpoint(orgId, role);

        // 📃 Get all operations associated with this role
        List<Operation> operations = roleDb.getOperations();

        // 🔄 Check if the given operation name exists in the allowed operations list
        for(Operation opr: operations){
            if(opr.getName().equals(oprName)){
                return true;
            }
        }

        // ❌ If not found in list, deny access
        return false;
    }
}


/*
 --------------------------------------------
 🧠 Keyword Reference - AuthService
 --------------------------------------------
 @Service                      => Marks this class as a Spring Service Component
 @Autowired                    => Injects dependencies (DBAPI, JwtUtil)

 jwtUtil.decryptToken()       => Custom method that extracts info from the token
 dbapi.callGetRoleByOrgId...  => REST call to DB microservice to get role & its operations

 Example token content (after decrypt):
   "parul@gmail.com: parul123: ADMIN"

 Example operation list for ADMIN:
   ["READ_USERS", "DELETE_USER", "ADD_USER"]
*/

/*
To summarize, AuthService:

Decrypts the JWT token to get the user’s role.

Calls the DB microservice to get what operations that role is allowed to perform.

Checks if the requested operation is among them.
*/
