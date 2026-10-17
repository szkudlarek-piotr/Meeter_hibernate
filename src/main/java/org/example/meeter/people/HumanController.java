package org.example.meeter.people;

import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.example.meeter.auth.userDetailsService.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/human")
public class HumanController {
    private HumanService humanService;

    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("/all")
    public List<HumanTileDto> getAllHumans() {
        return humanService.listAllHumans();
    }



    @GetMapping("/humansWoUuid")
    public List<Human> woUuid() {
        return humanService.getHumansWoUuid();
    }

    @Transactional
    @GetMapping("/me")
    public HumanTileDto showDetailsAboutMe(@AuthenticationPrincipal CurrentUser currentUser) {
        Human human = humanService.findHumanById(currentUser.getHuman().getId());
        return human.getHumanTileDto();
    }

    @Transactional
    @GetMapping("/myUsername")
    public ResponseEntity<String> getOwnUsername(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(currentUser.getUsername());
    }

    @GetMapping("/sortedHumans")
    public ResponseEntity<List<HumanTileDto>> getSortedTiles() {
        return ResponseEntity.ok(humanService.getSortedTiles());
    }
}
