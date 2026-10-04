package org.example.meeter.photo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoName;

    private double latitude;
    private double longitude;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhotoType photoType;

    private LocalDateTime generationTime;

    UUID uuid = UUID.randomUUID();
}
