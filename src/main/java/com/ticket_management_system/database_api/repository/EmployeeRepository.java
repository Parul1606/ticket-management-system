package com.ticket_management_system.database_api.repository;

import com.ticket_management_system.database_api.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    public Employee findByEmail(String email){

    }
}
