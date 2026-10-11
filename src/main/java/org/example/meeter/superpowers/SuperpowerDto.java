package org.example.meeter.superpowers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.meeter.auth.user.User;
import org.example.meeter.multimedia.photo.Photo;
import org.example.meeter.people.Human;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SuperpowerDto {

    private String name;
    private UUID uuid;
    private String photoAddress;
}
