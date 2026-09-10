package org.example.meeter.people;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.meeter.cliques.Clique;
import org.example.meeter.place.Place;
import org.example.meeter.songs.Song;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="people")
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid = UUID.randomUUID();
    private LocalDate birthdayDate;
    private String fbLink;

    @ManyToOne
    private Place home;
    private char gender;

    @ManyToOne
    private Clique clique;

    private String livesIn;

    @ManyToOne
    private Song song;

}
