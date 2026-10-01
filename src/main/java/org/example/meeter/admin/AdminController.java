package org.example.meeter.admin;

import org.example.meeter.people.Human;
import org.example.meeter.people.HumanRepository;
import org.example.meeter.user.User;
import org.example.meeter.user.UserRepository;
import org.example.meeter.userRoles.UserRole;
import org.example.meeter.userRoles.UserRolesRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.Normalizer;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    public final UserRepository userRepository;
    public final HumanRepository humanRepository;
    public final UserRolesRepository userRolesRepository;

    public AdminController(UserRepository userRepository, HumanRepository humanRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.humanRepository = humanRepository;
        this.userRolesRepository = userRolesRepository;
    }

    public boolean checkIfUsernameExists(String username) {
        List<User> usersByName = userRepository.findUsersByUsername(username);
        if (usersByName.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

//    @GetMapping("/createAllUsers")
//    public void createAllUsers() {
//        List<Human> allHumans = humanRepository.findAll();
//        for (Human human : allHumans) {
//            String usernameToSave = "";
//            String name = human.getName();
//            String surname = human.getSurname();
//            for (int i = 1; i< name.length(); i++) {
//                String testedUsername = name.substring(0, i) + surname;
//                String normalizedUserName = Normalizer
//                        .normalize(testedUsername, Normalizer.Form.NFD)
//                        .replaceAll("\\p{M}", "")
//                        .replace("ł", "l")
//                        .replace("Ł", "L")
//                        .replace("-", "_")
//                        .replace(" ", "_")
//                        .replace(" / ", "")
//                        .toLowerCase();
//                if (!checkIfUsernameExists(normalizedUserName)) {
//                    usernameToSave = normalizedUserName;
//                    break;
//                }
//            }
//            User userToSave = new User();
//            userToSave.setUsername(usernameToSave);
//
//            String salt = BCrypt.gensalt();
//            String hashedPassword = BCrypt.hashpw("haslo", salt);
//            userToSave.setPasswordHash(hashedPassword);
//
//            userToSave.setHuman(human);
//
//            UserRole role = userRolesRepository.getRoleByName("USER");
//
//            userToSave.addRole(role);
//
//            userRepository.save(userToSave);
//        }
//    }

}
