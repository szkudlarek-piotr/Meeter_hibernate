package org.example.meeter.people;

import org.example.meeter.quote.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HumanRepository extends JpaRepository <Human, Long> {
    public List<Human> findAll();

    @Query(value = "SELECT * FROM people p WHERE CONCAT('%', ?1, '%') LIKE CONCAT(name, ' ', surname)", nativeQuery = true)
    public List<Human> getHumansBySubstring(String substring);

    public List<Human> findByCliqueId(Long id);

    @Query(value = "SELECT * FROM people WHERE uuid IS NULL", nativeQuery = true)
    public List<Human> getAllHumansWithoutUuid();

    @Query("SELECT q FROM Quote q WHERE q.isPublic = true  AND q.author = ?1")
    public List<Quote> getPublicQuotesOfHumans(Human human);

    public Human findByUuid(UUID uuid);


}
