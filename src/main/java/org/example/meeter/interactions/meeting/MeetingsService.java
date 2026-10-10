package org.example.meeter.interactions.meeting;

import org.example.meeter.people.Human;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingsService {
    private final MeetingsRepository meetingsRepository;

    public MeetingsService(MeetingsRepository meetingsRepository) {
        this.meetingsRepository = meetingsRepository;
    }

    public List<MeetingDto> getCommonMeetingOfHumans(Human askingHuman, Human checkedHuman) {
        return meetingsRepository
                .getCommonMeetings(askingHuman, checkedHuman)
                .stream()
                .map(Meeting::toDto)
                .toList();
    }

    List<MeetingDto> getAllMeetingsOfUser(Human human) {
        return meetingsRepository.findMeetingsByHuman(human).stream().map(Meeting::toDto).toList();
    }
}
