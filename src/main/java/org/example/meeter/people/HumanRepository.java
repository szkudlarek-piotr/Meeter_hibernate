package org.example.meeter.people;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HumanRepository extends JpaRepository <Human, Long> {
    public List<Human> findAll();
}
