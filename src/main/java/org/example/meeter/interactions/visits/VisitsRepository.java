package org.example.meeter.interactions.visits;

import org.example.meeter.people.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VisitsRepository extends JpaRepository<Visit, Long> {
    public Optional<Visit> findVisitByUuid(UUID uuid);

    @Query("select v from Visit v where ?1 member of v.visitHumans and ?2 member of v.visitHumans")
    public List<Visit> getCommonVisits(Human h1, Human h2);

    @Query("select v from Visit v where ?1 member of v.visitHumans")
    public List<Visit> getAllHumanVisits(Human human);
}
