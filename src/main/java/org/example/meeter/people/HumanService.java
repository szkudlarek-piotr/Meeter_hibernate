package org.example.meeter.people;

import org.example.meeter.quote.Quote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HumanService {
    private HumanRepository humanRepository;

    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public List<HumanTileDto> listAllHumans() {
        List<Human> list = humanRepository.findAll();
        return list.stream().map(Human::getHumanTileDto).toList();
    }


    public List<Quote> getPublicQuotes(Human human) {
        return humanRepository.getPublicQuotesOfHumans(human);
    }

    public List<Human> getHumansWoUuid() {
        return humanRepository.getAllHumansWithoutUuid();
    }

}
