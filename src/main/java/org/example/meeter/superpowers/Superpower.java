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

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="superpowers")
public class Superpower {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private UUID uuid;

    @OneToOne
    @JoinColumn(name="photo_id")
    private Photo photo;

    @ManyToOne
    private User addedBy;

    @ManyToMany(mappedBy = "superpowers")
    private List<Human> humansWithSuperpower;
}
