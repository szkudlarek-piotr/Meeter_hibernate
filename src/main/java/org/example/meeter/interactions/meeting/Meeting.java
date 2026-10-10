package org.example.meeter.interactions.meeting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.meeter.interactions.Interaction;
import org.example.meeter.people.Human;
import org.example.meeter.place.Place;
import org.example.meeter.auth.user.User;

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

    @Size(max=10240)
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

    @JsonIgnore
    @ManyToOne
    private User createdBy;

    public LocalDate getInteractionPointsDate() {
        return this.date;
    }

    public void addHumanToMeeting(Human human) {
        meetingMembers.add(human);
    }

    public MeetingDto toDto() {
        MeetingDto dto = new MeetingDto();
        dto.setUuid(this.uuid);
        dto.setShortDesc(this.shortDesc);
        dto.setLongDesc(this.longDesc);
        dto.setMeetingMembers(this.meetingMembers.stream().map(Human::getHumanDtoForInteraction).toList());
        dto.setDate(this.getDate());
        return dto;
    }

}

