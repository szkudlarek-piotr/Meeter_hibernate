package org.example.meeter.meeting;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.meeter.interfaces.Interaction;
import org.example.meeter.people.Human;
import org.example.meeter.place.Place;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name="meetings")
public class Meeting implements Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    private String shortDesc;
    private String longDesc;

    @ManyToMany
    @JoinTable(name="meeting_human",
            joinColumns = @JoinColumn(name="meeting_id"),
            inverseJoinColumns = @JoinColumn(name="human_id"))
    List<Human> meetingMembers;


    private String placeString;

    @ManyToOne
    private Place place;

    private UUID uuid;

    public LocalDate getInteractionPointsDate() {
        return this.date;
    }

}

