package org.example.meeter.songs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.meeter.user.User;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name="songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String fileName;
    private String title;

    @JsonIgnore
    private User createdBy;
}
