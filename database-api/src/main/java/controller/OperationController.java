package com.ticket_management_system.database_api.controller;

import com.ticket_management_system.database_api.dto.OperationListDto;
import com.ticket_management_system.database_api.models.Operation;
import com.ticket_management_system.database_api.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/db/operation")
public class OperationController {

    @Autowired
    OperationRepository operationRepository;

    @GetMapping("/all")
    public ResponseEntity getAllOperations(){
        List<Operation> operationList  = operationRepository.findAll();
        OperationListDto operationListDto = new OperationListDto();
        operationListDto.setOperationList(operationList);
        return new ResponseEntity(operationListDto, HttpStatus.OK);
    }

}
