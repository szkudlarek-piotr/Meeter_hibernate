package org.example.meeter.citybreak;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.meeter.people.Human;
import org.example.meeter.place.Place;
import org.example.meeter.user.User;

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
    @Size(max=10240)
    private String longDesc;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @JsonIgnore
    private User createdBy;

    @Column(nullable = false, unique = true, updatable = false)
    private java.util.UUID uuid = UUID.randomUUID();

    @ManyToMany
    @JoinTable(name="trip_place", joinColumns = @JoinColumn(name="trip_id"), inverseJoinColumns = @JoinColumn(name="place_id"))
    List<Place> places;

    @ManyToMany
    @JoinTable(name="trip_human",
            joinColumns = @JoinColumn(name="trip_id"),
            inverseJoinColumns = @JoinColumn(name="human_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"trip_id", "human_id"})
    )
    List<Human> humansInTrip;

}
