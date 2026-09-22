package org.example.meeter.visits;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.meeter.people.Human;
import org.example.meeter.place.Place;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name="visits")
public class Visit implements Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate meetingDate;
    private String shortDesc;
    private String longDesc;
    @ManyToMany
    @JoinTable(name="visit_human", joinColumns = @JoinColumn(name="visit_id"), inverseJoinColumns = @JoinColumn(name="human_id"))
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
