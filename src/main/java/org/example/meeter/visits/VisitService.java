package org.example.meeter.visits;

import org.example.meeter.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
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
}
