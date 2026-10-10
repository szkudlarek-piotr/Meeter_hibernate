package org.example.meeter.auth.userRoles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRolesRepository extends JpaRepository<UserRole, Long> {
    @Query("select r from UserRole r WHERE r.roleName = concat('ROLE_', ?1)")
    public UserRole getRoleByName(String name);
}
