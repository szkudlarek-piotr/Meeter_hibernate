package org.example.meeter.meeting;

import org.example.meeter.people.Human;
import org.example.meeter.people.HumanService;
import org.example.meeter.auth.user.User;
import org.example.meeter.auth.userDetailsService.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/meetings")
public class MeetingsController {
    private final MeetingsService meetingsService;
    private final HumanService humanService;

    public MeetingsController(MeetingsService meetingsService, HumanService humanService) {
        this.meetingsService = meetingsService;
        this.humanService = humanService;
    }

    @GetMapping("/ofUser/{checkedUserUuid}")
    public ResponseEntity<List<MeetingDto>> getHumanMeetings(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable(name="checkedUserUuid") UUID checkedUserUuid) {
        User askingUser = currentUser.getUser();
        Human askingHuman = askingUser.getHuman();
        boolean isAdminAsking = currentUser.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        Human humanToCheck = humanService.getHumanByUuid(checkedUserUuid);
        if (isAdminAsking) {
            return ResponseEntity.ok().body(meetingsService.getAllMeetingsOfUser(humanToCheck));
        } else {
            return ResponseEntity.ok().body(meetingsService.getCommonMeetingOfHumans(askingHuman, humanToCheck));
        }
    }
}
