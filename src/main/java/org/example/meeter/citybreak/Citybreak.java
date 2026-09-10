package org.example.meeter.citybreak;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.meeter.meeting.Entity;
import org.example.meeter.place.Place;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name="trips")
public class Citybreak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(nullable = false, unique = true, updatable = false)
    private java.util.UUID uuid = UUID.randomUUID();

    @ManyToMany
    @JoinTable(name="trip_place", joinColumns = @JoinColumn(name="trip_id"), inverseJoinColumns = @JoinColumn(name="place_id"))
    List<Place> places;
}
