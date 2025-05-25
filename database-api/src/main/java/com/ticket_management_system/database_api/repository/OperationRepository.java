package com.ticket_management_system.database_api.repository;

import com.ticket_management_system.database_api.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {

}