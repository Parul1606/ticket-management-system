package com.ticket_management_system.database_api.controller;

import com.ticket_management_system.database_api.models.Employee;
import com.ticket_management_system.database_api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable UUID id){
        Employee emp = employeeRepository.findById(id).orElse(null);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity saveEmployee(@RequestBody Employee emp){
        employeeRepository.save(emp);
        return new ResponseEntity(emp, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return new ResponseEntity(employee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable UUID id){
        employeeRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/email/{emailId}")
    public ResponseEntity getEmployeeByEmail(@PathVariable String email){
        Employee emp  = employeeRepository.findByEmail(email);
        return new ResponseEntity(emp, HttpStatus.OK);
    }
}