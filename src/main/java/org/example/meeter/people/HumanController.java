package org.example.meeter.people;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/human")
public class HumanController {
    private HumanRepository humanRepository;

    public HumanController(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @GetMapping("/all")
    public List<Human> getAllHumans() {
        return humanRepository.findAll();
    }

    @PostMapping("/add/{name}/{surname}")
    public void addHuman(@PathVariable(name="name") String name, @PathVariable(name="surname") String surname) {
        Human humanToCreate = new Human();
        humanToCreate.setName(name);
        humanToCreate.setSurname(surname);
        humanRepository.save(humanToCreate);
    }
}
