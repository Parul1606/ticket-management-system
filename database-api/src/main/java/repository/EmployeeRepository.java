package com.ticket_management_system.database_api.repository;

import com.ticket_management_system.database_api.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

<<<<<<< HEAD
    public Employee findByEmail(String email);
}
=======
    public Employee findByEmail(String email){

    }
}
>>>>>>> 3d2c1cfe359a407f1360e932d41cb4cf34cb6b33
