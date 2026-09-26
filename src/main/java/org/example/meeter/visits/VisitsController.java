package org.example.meeter.visits;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/visits")
public class VisitsController {
    private VisitService visitService;

    public VisitsController(VisitService visitService) {
        this.visitService = visitService;
    }


    @GetMapping("/{uuid}")
    public ResponseEntity getVisitByUuid(@PathVariable(name="uuid") UUID uuid) {
        return ResponseEntity.ok(visitService.getVisitByUuid(uuid));
    }
}
