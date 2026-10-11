package org.example.meeter.migrators;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.meeter.multimedia.photo.Photo;
import org.example.meeter.multimedia.photo.PhotoRepository;
import org.example.meeter.multimedia.photo.PhotoType;
import org.example.meeter.superpowers.Superpower;
import org.example.meeter.superpowers.SuperpowersRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "app.migration.superpower-photos.enabled",
        havingValue = "true"
)
public class SuperpowerPhotoMigration implements ApplicationRunner {

    private final SuperpowersRepository superpowersRepository;
    private final PhotoRepository photoRepository;

    private static final Path SOURCE = Paths.get(
            "C:\\Users\\piotr\\Desktop\\projekty\\react_meeter2\\react_meeter_2\\backend\\superpower_icons"
    );

    private static final Path DESTINATION = Paths.get(
            "C:\\Users\\piotr\\Desktop\\projekty\\meeter\\src\\main\\resources\\static\\photos\\superpowers"
    );

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws IOException {
        Files.createDirectories(DESTINATION);

        for (Superpower sp : superpowersRepository.findAllSuperpowers()) {
            // Nie nadpisuj zdjęcia, jeśli supermoc już je ma.
            if (sp.getPhoto() != null) {
                continue;
            }

            Path source = SOURCE.resolve(sp.getId() + ".png");

            if (!Files.isRegularFile(source)) {
                System.out.println("Brak pliku: " + source);
                continue;
            }

            UUID uuid = UUID.randomUUID();
            String filename = uuid + ".png";
            Path destination = DESTINATION.resolve(filename);

            Files.copy(source, destination);

            try {
                Photo photo = new Photo();
                photo.setPhotoType(PhotoType.SUPERPOWER);
                photo.setUuid(uuid);
                photo.setPhotoName(filename);
                photo.setGenerationTime(LocalDateTime.now());

                Photo savedPhoto = photoRepository.save(photo);
                sp.setPhoto(savedPhoto);

                superpowersRepository.save(sp);
            } catch (RuntimeException e) {
                Files.deleteIfExists(destination);
                throw e;
            }
        }

        System.out.println("Migracja ikon supermocy zakończona.");
    }
}
