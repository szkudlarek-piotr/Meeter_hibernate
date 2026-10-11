package org.example.meeter.superpowers;

import org.example.meeter.people.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuperpowersRepository extends JpaRepository<Superpower, Long> {
    @Query("select s from Superpower s")
    public List<Superpower> findAllSuperpowers();
}
