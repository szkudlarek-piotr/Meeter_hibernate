package org.example.meeter.userRoles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserRoleService {
    private final UserRolesRepository userRolesRepository;


    public UserRoleService(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    public UserRole addUserRole(String name) {
        UserRole newRole = new UserRole();
        newRole.setRoleName(name);
        log.debug("Dodaję rolę użytkownika o nazwie %s.".formatted(name));
        return userRolesRepository.save(newRole);
    }
}
