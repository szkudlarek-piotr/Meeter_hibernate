package org.example.meeter.people;

import jakarta.transaction.Transactional;
import org.example.meeter.auth.userDetailsService.CurrentUser;
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
}
