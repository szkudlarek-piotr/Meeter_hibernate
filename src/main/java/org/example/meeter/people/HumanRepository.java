package org.example.meeter.people;

import org.example.meeter.quote.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
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

    public Optional<Human> findHumanByUuid(UUID uuid);

    public Optional<Human> findHumanById(Long id);

    @Query(value = """
WITH all_interactions AS (
            SELECT
                vg.human_id AS human_id,
                COALESCE(
                    ROUND(
                        get_exponential_decay_fraction(
                            v.visit_date, CURRENT_DATE(), 720
                        ), 3
                    ) * 6,
                    0
                ) AS points
            FROM visit_human vg
            JOIN visits v ON v.id = vg.visit_id

            UNION ALL

            SELECT
                mh.human_id,
                COALESCE(
                    ROUND(
                        get_exponential_decay_fraction(
                            m.date, CURRENT_DATE(), 360
                        ), 3
                    ) * 3,
                    0
                ) AS points
            FROM meeting_human mh
            JOIN meetings m ON m.ID = mh.meeting_id

            UNION ALL

            SELECT
                ec.human_id,
                COALESCE(
                    ROUND(
                        get_exponential_decay_fraction(
                            e.me_leaving_date, CURRENT_DATE(), 180
                        ), 3
                    ),
                    0
                ) AS points
            FROM event_human ec
            JOIN events e ON e.id = ec.event_id

            UNION ALL

            SELECT
                cc.human_id,
                COALESCE(
                    ROUND(
                        get_exponential_decay_fraction(
                            c.end_date, CURRENT_DATE(), 3650
                        ), 3
                    ) * 4,
                    0
                ) AS points
            FROM trip_human cc
            JOIN trips c ON c.ID = cc.trip_id
        ),
        human_points AS (
            SELECT
                human_id,
                SUM(points) AS total_points
            FROM all_interactions
            GROUP BY human_id
        )
        SELECT h.*
        FROM people h
        LEFT JOIN human_points hp ON hp.human_id = h.id
        ORDER BY COALESCE(hp.total_points, 0) DESC;""", nativeQuery = true)
    public List<Human> getSortedHumansNew();


}
