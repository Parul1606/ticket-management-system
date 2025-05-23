package com.ticket_management_system.database_api.repository;

import com.ticket_management_system.database_api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Query(value = "select * from roles where organization_id=:orgId and name=:name", nativeQuery = true)
    public Role getRoleByOrgIdAndRoleName(UUID orgId, String name);
}
