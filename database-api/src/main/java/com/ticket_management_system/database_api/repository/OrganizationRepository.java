package com.ticket_management_system.database_api.repository;

import com.ticket_management_system.database_api.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
}
