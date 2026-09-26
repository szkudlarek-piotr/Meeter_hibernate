package org.example.meeter.visits;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VisitsRepository extends JpaRepository<Visit, Long> {
    public Optional<Visit> findVisitByUuid(UUID uuid);
}
