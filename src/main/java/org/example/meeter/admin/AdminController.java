package org.example.meeter.admin;

import org.example.meeter.people.HumanRepository;
import org.example.meeter.multimedia.photo.PhotoRepository;
import org.example.meeter.auth.user.User;
import org.example.meeter.auth.user.UserRepository;
import org.example.meeter.auth.userRoles.UserRolesRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    public final UserRepository userRepository;
    public final HumanRepository humanRepository;
    public final UserRolesRepository userRolesRepository;
    public final PhotoRepository photoRepository;


    public AdminController(UserRepository userRepository, HumanRepository humanRepository, UserRolesRepository userRolesRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.humanRepository = humanRepository;
        this.userRolesRepository = userRolesRepository;
        this.photoRepository = photoRepository;
    }

    public boolean checkIfUsernameExists(String username) {
        List<User> usersByName = userRepository.findUsersByUsername(username);
        if (usersByName.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

//    @GetMapping("/migratePhotos")
//    public void migrateHumanPhotos() {
//        Path meeterPhotosFolderPath = Paths.get("C:\\Users\\piotr\\Desktop\\projekty\\react_meeter2\\react_meeter_2\\backend\\photos");
//        List<Human> allHumans = humanRepository.findAll();
//        for (Human human : allHumans) {
//            Long id = human.getId();
//            String potentialPhotoName = id + ".jpg";
//            Path potentialPhotoPath = meeterPhotosFolderPath.resolve(potentialPhotoName);
//            if (Files.exists(potentialPhotoPath)) {
//                Photo newPhoto = new Photo();
//                UUID photoUuid = UUID.randomUUID();
//                String newPhotoName = photoUuid + ".jpg";
//                newPhoto.setPhotoName(newPhotoName);
//                newPhoto.setPhotoType(PhotoType.PROFILE_PICTURE);
//                newPhoto.setUuid(photoUuid);
//                newPhoto.setGenerationTime(LocalDateTime.now());
//
//                Photo savedPhoto =  photoRepository.save(newPhoto);
//
//                human.setProfilePhoto(savedPhoto);
//                humanRepository.save(human);
//                try {
//                    Files.copy(potentialPhotoPath, Paths.get("C:\\Users\\piotr\\Desktop\\projekty\\meeter\\src\\main\\resources\\static\\photos\\people\\%s".formatted(newPhotoName)));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

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
