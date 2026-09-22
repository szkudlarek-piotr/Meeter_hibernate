package org.example.meeter.userRoles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRolesRepository extends JpaRepository<UserRole, Long> {
    public UserRole findByUuid(UUID uuid);
}
