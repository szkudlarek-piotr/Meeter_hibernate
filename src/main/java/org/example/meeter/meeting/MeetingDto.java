package org.example.meeter.meeting;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.meeter.people.HumanDtoForInteraction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MeetingDto {
    private LocalDate date;
    private String shortDesc;
    private String longDesc;
    private String placeString;
    private List<HumanDtoForInteraction> meetingMembers;
    private UUID uuid;
}
