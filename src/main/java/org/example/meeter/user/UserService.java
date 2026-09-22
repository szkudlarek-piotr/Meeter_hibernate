package org.example.meeter.user;

import org.example.meeter.people.Human;
import org.example.meeter.people.HumanRepository;
import org.example.meeter.userRoles.UserRole;
import org.example.meeter.userRoles.UserRolesRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final HumanRepository humanRepository;

    public UserService(UserRepository userRepository, UserRolesRepository userRolesRepository, HumanRepository humanRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
        this.humanRepository = humanRepository;
    }

    public UserDto addUser(Map<String, String> map) {
        User userToAdd = new User();

        UUID roleUuid = UUID.fromString(map.get("roleUuid"));
        UserRole role = userRolesRepository.findByUuid(roleUuid);
        userToAdd.setUserRole(role);
        String password = map.get("password");
        String username = map.get("username");
        String email = map.get("email");
        UUID humanUuid = UUID.fromString(map.get("humanUuid"));
        Human human = humanRepository.findByUuid(humanUuid);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        userToAdd.setUsername(username);
        userToAdd.setPasswordHash(hashedPassword);
        userToAdd.setEmail(email);
        userToAdd.setHuman(human);
        return userRepository.save(userToAdd).getDto();

    }
}
