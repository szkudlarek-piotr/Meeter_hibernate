package org.example.meeter.people;

import org.springframework.http.ResponseEntity;
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
}
