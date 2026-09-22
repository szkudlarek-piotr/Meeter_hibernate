package org.example.meeter.visits;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.meeter.interfaces.Interaction;
import org.example.meeter.people.Human;
import org.example.meeter.place.Place;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name="visits")
public class Visit implements Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortDesc;
    @Size(min=32, max=10240)
    private String longDesc;
    private LocalDateTime visitDate;
    @Size(min=1, message = "Wizyta powinna trwać co najmniej jeden dzień.")
    private int duration;

    @ManyToMany
    @JoinTable(name="visit_human",
            joinColumns = @JoinColumn(name="visit_id"),
            inverseJoinColumns = @JoinColumn(name="human_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"visit_id", "human_id"}))
    private List<Human> visitHumans;

    @ManyToOne
    @JoinColumn(name="place_id")
    private Place visitPlace;

    private UUID uuid;

    @Override
    public LocalDate getInteractionPointsDate() {
        LocalDate visitLastDay =  LocalDate.from(visitDate).plusDays(this.duration-1);
        return visitLastDay;
    }
}
