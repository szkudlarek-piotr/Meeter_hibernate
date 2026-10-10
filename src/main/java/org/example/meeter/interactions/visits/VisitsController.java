package org.example.meeter.interactions.visits;

import org.example.meeter.people.Human;
import org.example.meeter.people.HumanService;
import org.example.meeter.auth.user.User;
import org.example.meeter.auth.userDetailsService.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/visits")
public class VisitsController {
    private VisitService visitService;
    private HumanService humanService;

    public VisitsController(VisitService visitService, HumanService humanService) {
        this.visitService = visitService;
        this.humanService = humanService;
    }


    @GetMapping("/{uuid}")
    public ResponseEntity getVisitByUuid(@PathVariable(name="uuid") UUID uuid) {
        return ResponseEntity.ok(visitService.getVisitByUuid(uuid));
    }

    @GetMapping("/common/{humanUuid}")
    public ResponseEntity<List<VisitDto>> getCommonVisits(@AuthenticationPrincipal CurrentUser currentUser
            , @PathVariable(name="humanUuid") UUID checkedHumanUuid) {
        User askingUser = currentUser.getUser();
        boolean isAdminAsking = currentUser.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));


        Human humanToCheck = humanService.getHumanByUuid(checkedHumanUuid);
        return ResponseEntity.ok(visitService.findCommonVisits(askingUser, humanToCheck));
    }
}
