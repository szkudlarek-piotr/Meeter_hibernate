package org.example.meeter.interactions.visits;

import org.example.meeter.errors.ResourceNotFoundException;
import org.example.meeter.people.Human;
import org.example.meeter.auth.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VisitService {
    private final VisitsRepository visitsRepository;

    public VisitService(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }

    public VisitDto getVisitByUuid(UUID uuid) {
        return visitsRepository.findVisitByUuid(uuid).orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono wizyty o UUID = %s.".formatted(uuid))).getDto();
    }

    public List<VisitDto> findCommonVisits(User askingUser, Human humanToCheck) {
        Human h1 = askingUser.getHuman();
        List<VisitDto> returnedDtos = visitsRepository.getCommonVisits(h1, humanToCheck).stream().map(Visit::getDto).toList();
        return returnedDtos;
    }

    public List<VisitDto> getAllHumanVisits(Human human) {
        return visitsRepository.getAllHumanVisits(human).stream().map(Visit::getDto).toList();
    }



}
