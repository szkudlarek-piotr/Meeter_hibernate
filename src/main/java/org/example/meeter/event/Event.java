package org.example.meeter.event;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.meeter.interfaces.Interaction;
import org.example.meeter.people.Human;
import org.example.meeter.place.Place;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="events")
public class Event implements Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, max=128, message="Króka nazwa wydarzenia powinna mieć od 2 do 2 do 128 znaków")
    private String shortDesc;
    @Size(min=4, max=10240, message = "Długo opis powinien mieć od 4 do 10240 znaków.")
    private String longDesc;
    private LocalDateTime startDate;
    private LocalDateTime stopDate;
    private LocalDateTime meComingDate;
    private LocalDateTime meLeavingDate;

    private String photoName;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="event_human",
            joinColumns = @JoinColumn(name="event_id"),
            inverseJoinColumns = @JoinColumn(name="human_id")
    )
    private List<Human> humansInEvent;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid = UUID.randomUUID();

    @JsonIgnore
    @ManyToOne
    private Place place;


    @Override
    public LocalDate getInteractionPointsDate() {
        return meLeavingDate.toLocalDate();
    }
}
