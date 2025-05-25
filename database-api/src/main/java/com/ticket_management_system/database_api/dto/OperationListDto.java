package com.ticket_management_system.database_api.dto;

import com.ticket_management_system.database_api.models.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationListDto {
    List<Operation> operationList;
}