package org.example.meeter.visits;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitsController {
    private VisitsRepository visitsRepository;

    public VisitsController(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }

    @GetMapping("/all")
    public List<Visit> getAllVisits() {
        return visitsRepository.findAll();
    }
}
